package plugin.utils.migration;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiExpressionList;
import com.intellij.psi.PsiThisExpression;
import org.apache.commons.lang3.StringUtils;
import plugin.constants.aqality.AqalityEntitiesNames;
import plugin.constants.aqality.AqalityTypes;
import plugin.constants.literals.Methods;
import plugin.constants.selenide.SelenideElementParts;
import plugin.utils.psi.PsiParserUtils;

import java.util.LinkedList;
import java.util.List;

public class WebElementSearchMigrationUtils {

    private WebElementSearchMigrationUtils() {}

    public static boolean isSelenideLine(PsiElement psiElement) {
        return psiElement.getText().contains(SelenideElementParts.SELENIDE_ELEMENT) ||
                psiElement.getText().contains(SelenideElementParts.ELEMENTS_COLLECTION) ||
                psiElement.getText().contains(SelenideElementParts.FIND_ELEMENT_BY_CSS) ||
                psiElement.getText().contains(SelenideElementParts.FIND_ELEMENTS_BY_CSS);
    }

    public static String getRefactoredMethodLineLabelType(PsiElement psiElement) {
        return getRefactoredMethodLineLabelType(psiElement, AqalityTypes.LABEL);
    }

    public static String getRefactoredMethodLineLabelType(PsiElement psiElement, AqalityTypes type) {
        List<PsiElement> rowElements = PsiParserUtils.getPsiElements(psiElement);
        List<String> resultLine = new LinkedList<>();
        final String foundElementDescription = "\"type element description here\"";
        boolean isFindChildElement = false;

        for (int i = 0; i < rowElements.size(); i++) {
            switch (rowElements.get(i).getText()) {
                case SelenideElementParts.SELENIDE_ELEMENT:
                    resultLine.add(type.getType());
                    break;
                case SelenideElementParts.ELEMENTS_COLLECTION:
                    resultLine.add(type.getListDiamondsType());
                    break;
                case SelenideElementParts.FIND_ELEMENT_BY_XPATH:
                    resultLine.add(getFindMethodElement(type.getTypeGetterMethod(), isFindChildElement));
                    i = findClosestExpressionList(rowElements, i + 1, resultLine,
                            AqalityEntitiesNames.XPATH_LOCATOR,
                            getSingleElementParameters(foundElementDescription, type.getCollectionType(), isFindChildElement));
                    isFindChildElement = true;
                    break;
                case SelenideElementParts.FIND_ELEMENTS_BY_XPATH:
                    resultLine.add(getFindMethodElements(isFindChildElement));
                    i = findClosestExpressionList(rowElements, i + 1, resultLine,
                            AqalityEntitiesNames.XPATH_LOCATOR,
                            type.getCollectionType());
                    isFindChildElement = true;
                    break;
                case SelenideElementParts.FIND_ELEMENT_BY_CSS:
                    resultLine.add(getFindMethodElement(type.getTypeGetterMethod(), isFindChildElement));
                    i = findClosestExpressionList(rowElements, i + 1, resultLine,
                            AqalityEntitiesNames.CSS_SELECTOR,
                            getSingleElementParameters(foundElementDescription, type.getCollectionType(), isFindChildElement));
                    isFindChildElement = true;
                    break;
                case SelenideElementParts.FIND_ELEMENTS_BY_CSS:
                    resultLine.add(getFindMethodElements(isFindChildElement));
                    i = findClosestExpressionList(rowElements, i + 1, resultLine,
                            AqalityEntitiesNames.CSS_SELECTOR,
                            type.getCollectionType());
                    isFindChildElement = true;
                    break;
                default:
                    if (rowElements.get(i) instanceof PsiExpressionList || rowElements.get(i) instanceof PsiThisExpression) {
                        isFindChildElement = true;
                    }
                    resultLine.add(rowElements.get(i).getText());
            }
        }
        return String.join(StringUtils.EMPTY, resultLine);
    }

    private static int findClosestExpressionList(List<PsiElement> rowElements, int startIndex, List<String> resultLine,
                                                String findBy, String secondParameter) {
        int i;
        for (i = startIndex; i < rowElements.size(); i++) {
            if (rowElements.get(i) instanceof PsiExpressionList) {
                resultLine.add(String.format("(By.%1$s%2$s, %3$s)", findBy,
                        rowElements.get(i).getText(), secondParameter));
                break;
            } else {
                resultLine.add(rowElements.get(i).getText());
            }
        }
        return i;
    }

    private static String getFindMethodElement(String typeGetterMethod, boolean isFindChildElement) {
        return isFindChildElement
                ? AqalityEntitiesNames.FIND_CHILD_ELEMENT
                : String.join(Methods.METHODS_CALL_SEPARATOR,
                AqalityEntitiesNames.ELEMENTS_FACTORY,
                typeGetterMethod);
    }

    private static String getFindMethodElements(boolean isFindChildElement) {
        return isFindChildElement
                ? AqalityEntitiesNames.FIND_CHILD_ELEMENTS
                : String.join(Methods.METHODS_CALL_SEPARATOR,
                AqalityEntitiesNames.ELEMENTS_FACTORY,
                AqalityEntitiesNames.FIND_ELEMENTS);
    }

    private static String getSingleElementParameters(String description, String elementType, boolean isFindChildElement) {
        return isFindChildElement ? String.join(Methods.PARAMETERS_SEPARATOR, description, elementType) : description;
    }
}
