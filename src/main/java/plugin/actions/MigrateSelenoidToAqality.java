package plugin.actions;

import com.intellij.codeInsight.completion.AllClassesGetter;
import com.intellij.codeInsight.completion.PlainPrefixMatcher;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Processor;
import org.jetbrains.annotations.NotNull;
import plugin.migration.FieldsMigration;
import plugin.migration.MethodsFieldsMigration;

import java.util.Objects;

public class MigrateSelenoidToAqality extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = Objects.requireNonNull(anActionEvent.getProject());


        AllClassesGetter.processJavaClasses(
                new PlainPrefixMatcher(""),
                Objects.requireNonNull(project),
                GlobalSearchScope.projectScope(project),
                migrateSelenideToAqality()
        );
    }


    private Processor<PsiClass> migrateSelenideToAqality() {
        return psiClass -> {
            FieldsMigration.migrateFields(psiClass);
            MethodsFieldsMigration.migrateFieldsInMethods(psiClass);
            return true;
        };
    }









}