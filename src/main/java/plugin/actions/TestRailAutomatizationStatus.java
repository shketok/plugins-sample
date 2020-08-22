package plugin.actions;

import com.intellij.codeInsight.completion.AllClassesGetter;
import com.intellij.codeInsight.completion.PlainPrefixMatcher;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.DefaultLogger;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Processor;
import org.apache.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import plugin.constants.testrail.AnnotationsNames;
import plugin.ui.TestRailSetStatusesProgressBar;
import plugin.ui.TestrailAuthenticationForm;
import testrail.api.constants.StatusAt;
import testrail.api.methods.TestRailCases;
import testrail.api.client.TestrailClient;
import testrail.api.models.get.trcase.ResponseGetCase;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestRailAutomatizationStatus extends AnAction {
    private static final Logger LOGGER = new DefaultLogger(TestrailAuthenticationForm.class.getName());

    //    private TestRailSetStatusesProgressBar testRailSetStatusesProgressBar;
    private List<PsiMethod> methodsWithLinkToTheTestrail;

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        try {

            Project project = anActionEvent.getProject();

            methodsWithLinkToTheTestrail = new LinkedList<>();
//            testRailSetStatusesProgressBar = new TestRailSetStatusesProgressBar();
//            testRailSetStatusesProgressBar.setVisible(true);

            AllClassesGetter.processJavaClasses(
                    new PlainPrefixMatcher(""),
                    Objects.requireNonNull(project),
                    GlobalSearchScope.projectScope(project),
                    collectMethodsWithAnnotations()
            );

//            testRailSetStatusesProgressBar.setProgressBarMax(methodsWithLinkToTheTestrail.size());
            startTestRailStatusUpdating();
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
//            testRailSetStatusesProgressBar.close();
        }
    }

    public Processor<PsiClass> collectMethodsWithAnnotations() {
        return psiClass -> {
            PsiMethod[] methodsOfTheClass = psiClass.getAllMethods();
            methodsWithLinkToTheTestrail.addAll(Arrays.stream(methodsOfTheClass)
                    .filter(method -> method.getAnnotation(AnnotationsNames.LINK_ANNOTATION) != null)
                    .collect(Collectors.toList()));
            return true;
        };
    }

    public void startTestRailStatusUpdating() {
        IntStream.range(0, methodsWithLinkToTheTestrail.size())
                .forEach(index -> {
                    String annotationWithValue = Objects.requireNonNull(methodsWithLinkToTheTestrail.get(index)
                            .getAnnotation(AnnotationsNames.LINK_ANNOTATION)).getText();
                    String caseId = getTrIdFromAnnotation(annotationWithValue);
//                    testRailSetStatusesProgressBar.updateStatus(String.format("Updating id %1$s test status", caseId));
                    setTestrailStatusDone(caseId);
//                    testRailSetStatusesProgressBar.updateProgressBar(index);
                });
    }

    private String getTrIdFromAnnotation(String annotationText) {
        String linkToTheTestRail = annotationText.substring(annotationText.indexOf("(") + 1,
                annotationText.indexOf(")")).replace("\"", "");
        return linkToTheTestRail.substring(linkToTheTestRail.lastIndexOf("/") + 1);
    }

    private void setTestrailStatusDone(String caseId) {
        Map<String, Object> data = new HashMap<>();
        data.put(StatusAt.DONE.getFieldName(), StatusAt.DONE.getFieldValue());

        ResponseGetCase responseGetCase = TestRailCases.getCase(caseId);
        if (responseGetCase != null &&
                (responseGetCase.getCustomStatusAt() == null ||
                        !responseGetCase.getCustomStatusAt().equals(StatusAt.DONE.getFieldValue()))) {
            TestRailCases.updateCase(caseId, data);
            if (TestrailClient.getInstance().getStatus() != HttpStatus.SC_OK) {
                LOGGER.error(String.format("После выполнения обновления кейса %1$s был получен статус %2$s", caseId,
                        TestrailClient.getInstance().getStatus()));
            }
        } else {
            LOGGER.info(String.format("Не был обновлен статус, так как тест %1$s не существует, "
                    + "или у него уже имеется требуемый статус %2$s", caseId, StatusAt.DONE.getFieldName()));
        }
    }

}
