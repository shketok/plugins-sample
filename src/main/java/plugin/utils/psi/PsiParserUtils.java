package plugin.utils.psi;

import com.intellij.psi.PsiBinaryExpression;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiLocalVariable;
import com.intellij.psi.PsiMethodCallExpression;
import com.intellij.psi.PsiReferenceExpression;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PsiParserUtils {

    public static boolean isLineContainsMethodReference(PsiElement psiElement) {
        if (psiElement instanceof PsiLocalVariable) {
            PsiLocalVariable psiLocalVariable = (PsiLocalVariable) psiElement;
            return Arrays.stream(psiLocalVariable.getChildren()).anyMatch(lineElement ->
                    lineElement instanceof PsiMethodCallExpression);
        } else if (psiElement instanceof PsiMethodCallExpression) {
            return true;
        } else {
            return false;
        }
    }


    public static List<PsiElement> getPsiElements(PsiElement psiElement) {
        List<PsiElement> psiElements = new LinkedList<>();
        Arrays.stream(psiElement.getChildren()).forEach(expr -> {
            if (expr instanceof PsiReferenceExpression
                    || expr instanceof PsiLocalVariable
                    || expr instanceof PsiMethodCallExpression
                    || expr instanceof PsiBinaryExpression) {
                psiElements.addAll(getPsiElements(expr));
            } else {
                psiElements.add(expr);
            }
        });
        return psiElements;
    }


}
