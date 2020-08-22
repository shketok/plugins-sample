package plugin.migration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiImportList;
import com.intellij.psi.PsiJavaFile;
import plugin.constants.selenide.SelenideClassesNames;
import plugin.utils.psi.PsiUtils;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.Objects;

public class ImportsMigration {

    private ImportsMigration() {}

    public static boolean isFileContainsSelenideImports(PsiElement psiElement) {
        PsiJavaFile psiJavaFile = (PsiJavaFile) psiElement.getContainingFile();
        PsiImportList psiImportList = psiJavaFile.getImportList();
        if (psiImportList != null) {
            return Arrays.stream(psiImportList.getAllImportStatements())
                    .map(psiImportStatementBase -> Objects.requireNonNull(
                            psiImportStatementBase.getImportReference()).getQualifiedName())
                    .anyMatch(name -> name.contains(SelenideClassesNames.CODEBORNE_BASE));
        }
        return false;
    }


    public static void migrateImports(@Nonnull PsiClass psiClass, @Nonnull String... importsList) {
        final Project project = psiClass.getProject();

        CommandProcessor.getInstance().executeCommand(project, () -> ApplicationManager.getApplication().runWriteAction(() -> {
            Arrays.stream(importsList).forEach(imprt ->  PsiUtils.addImport(psiClass.getContainingFile(), imprt));
            PsiUtils.optimizeImports(psiClass.getContainingFile());
        }), "Import migration", null);
    }

}
