package plugin.migration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiStatement;
import plugin.constants.migration.MigrationImports;
import plugin.utils.migration.WebElementSearchMigrationUtils;
import plugin.utils.psi.PsiUtils;

import java.util.Arrays;
import java.util.Optional;

public class MethodsFieldsMigration {

    private MethodsFieldsMigration() {
    }

    public static void migrateFieldsInMethods(PsiClass psiClass) {
        if (ImportsMigration.isFileContainsSelenideImports(psiClass)) {
            final Project project = psiClass.getProject();
            Arrays.stream(psiClass.getAllMethods()).forEach(psiMethod ->
                    Optional.ofNullable(psiMethod.getBody()).ifPresent(body -> Arrays.stream(body.getStatements())
                            .filter(WebElementSearchMigrationUtils::isSelenideLine)
                            .forEach(psiStatement -> {
                                CommandProcessor.getInstance().executeCommand(project, () ->
                                        ApplicationManager.getApplication().runWriteAction(() -> {
                                            String resultLine = WebElementSearchMigrationUtils.getRefactoredMethodLineLabelType(psiStatement);
                                            PsiStatement newStatement = PsiUtils.createStatement(resultLine, psiStatement);
                                            psiStatement.addAfter(newStatement, psiStatement);
                                            psiStatement.delete();

                                            PsiUtils.addImport(psiClass.getContainingFile(), MigrationImports.IMPORT_BY);
                                            PsiUtils.optimizeImports(psiClass.getContainingFile());
                                        }), "Migrate method bodies", null);
                            })));
        }

    }


}
