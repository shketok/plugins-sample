package plugin.migration;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.CommandProcessor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import plugin.aqality.AqalityTypeFactory;
import plugin.constants.aqality.AqalityTypes;
import plugin.constants.migration.MigrationImports;
import plugin.constants.selenide.SelenideClassesNames;
import plugin.utils.migration.WebElementSearchMigrationUtils;
import plugin.utils.psi.PsiFieldUtils;
import plugin.utils.psi.PsiUtils;

import java.util.Arrays;

public class FieldsMigration {

    private FieldsMigration() {
    }

    public static void migrateFields(PsiClass psiClass) {
        if (ImportsMigration.isFileContainsSelenideImports(psiClass)) {
            final Project project = psiClass.getProject();
            Arrays.stream(psiClass.getAllFields())
                    .filter(field -> field.getType().getCanonicalText().contains(SelenideClassesNames.SELENIDEELEMENT)
                            || field.getType().getCanonicalText().contains(SelenideClassesNames.ELEMENTSCOLLECTION))
                    .forEach(field -> CommandProcessor.getInstance().executeCommand(project, () ->
                            ApplicationManager.getApplication().runWriteAction(() -> {
                                AqalityTypes aqalityType = AqalityTypeFactory.getType(PsiFieldUtils.getName(field));
                                String fieldText = WebElementSearchMigrationUtils
                                        .getRefactoredMethodLineLabelType(field, aqalityType);

                                PsiField modifyField = PsiUtils.createField(fieldText, field);
                                field.addAfter(modifyField, field);
                                field.delete();

                                ImportsMigration.migrateImports(psiClass, String.format(MigrationImports.IMPORT_ILABEL,
                                        aqalityType.getType()), MigrationImports.IMPORT_ELEMENT_TYPE);

                            }), "Migrate class fields", null));
        }
    }
}
