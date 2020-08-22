package plugin.utils.psi;

import com.intellij.openapi.project.Project;
import com.intellij.psi.JavaPsiFacade;
import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementFactory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiStatement;
import com.intellij.psi.PsiType;
import com.intellij.psi.codeStyle.JavaCodeStyleManager;
import com.intellij.psi.search.GlobalSearchScope;

import java.util.Optional;

public final class PsiUtils {

    private PsiUtils() {
    }

    public static PsiAnnotation createAnnoАation(final String annotation, final PsiElement context) {
        final PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(context.getProject());
        return factory.createAnnotationFromText(annotation, context);
    }

    public static PsiField createField(final String field, final PsiField context) {
        final PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(context.getProject());
        return factory.createFieldFromText(field, context);
    }

    public static PsiStatement createStatement(final String field, final PsiStatement context) {
        final PsiElementFactory factory = PsiElementFactory.SERVICE.getInstance(context.getProject());
        return factory.createStatementFromText(field, context);
    }

    public static void optimizeImports(final PsiFile file) {
        if (file instanceof PsiJavaFile) {
            optimizeImports((PsiJavaFile) file);
        }
    }

    public static void optimizeImports(final PsiJavaFile file) {
        JavaCodeStyleManager.getInstance(file.getProject()).shortenClassReferences(file);
        JavaCodeStyleManager.getInstance(file.getProject()).removeRedundantImports(file);
    }

    public static void addImport(final PsiFile file, final String qualifiedName) {
        if (file instanceof PsiJavaFile) {
            addImport((PsiJavaFile) file, qualifiedName);
        }
    }

    public static void addImport(final PsiJavaFile file, final String qualifiedName) {
        final Project project = file.getProject();
        Optional<PsiClass> possibleClass = Optional.ofNullable(JavaPsiFacade.getInstance(project)
                .findClass(qualifiedName, GlobalSearchScope.everythingScope(project)));
        possibleClass.ifPresent(psiClass -> JavaCodeStyleManager.getInstance(project).addImport(file, psiClass));
    }


}
