package plugin.constants.aqality;

public final class AqalityEditorPatterns {

    private AqalityEditorPatterns() {}

    public static final String ELEMENTS_COLLECTION_AQA = "%1$s List<%2$s> %3$s = elementFactory.findElements(By.%4$s(%5$s), %6$s);";
    public static final String ELEMENT_AQA = "%1$s %2$s %3$s = elementFactory.%6$s(By.%4$s(%5$s), \"type element description here\");";
}
