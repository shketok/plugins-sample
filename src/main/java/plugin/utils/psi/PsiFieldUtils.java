package plugin.utils.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiField;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class PsiFieldUtils {

    private PsiFieldUtils() {
    }

    public static String getAccessModifiersText(final PsiField psiField) {
        String modifiers = "";
        if (psiField.getModifierList() != null) {
            modifiers = Arrays.stream(psiField.getModifierList().getChildren()).map(PsiElement::getText)
                    .collect(Collectors.joining(StringUtils.SPACE)).trim();
        }
        return modifiers;
    }

    public static String getType(final PsiField psiField) {
        return psiField.getType().getPresentableText();
    }

    public static String getTypeImportText(final PsiField psiField) {
        return psiField.getType().getCanonicalText();
    }

    public static String getName(final PsiField psiField) {
        return psiField.getName();
    }

    public static String getValue(final PsiField psiField) {
        String value = "";
        if (psiField.getInitializer() != null) {
            value = psiField.getInitializer().getText();
        }
        return value;
    }

    public static String getValueLocator(final PsiField psiField) {
        String value = "";
        if (psiField.getInitializer() != null) {
            value = psiField.getInitializer().getChildren()[1].getText();
        }
        return value;
    }






}
