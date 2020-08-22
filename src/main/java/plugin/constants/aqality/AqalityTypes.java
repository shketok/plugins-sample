package plugin.constants.aqality;

public enum AqalityTypes {
    BUTTON("IButton", "ElementType.BUTTON", "getButton"),
    CHECKBOX("ICheckBox", "ElementType.CHECKBOX", "getCheckbox"),
    COMBOBOX("IComboBox", "ElementType.COMBOBOX", "getCombobox"),
    LABEL("ILabel", "ElementType.LABEL", "getLabel"),
    LINK("ILink", "ElementType.LINK", "getLink"),
    RADIOBUTTON("IRadioButton", "ElementType.RADIOBUTTON", "getRadiobutton"),
    TEXTBOX("ITextBox", "ElementType.TEXTBOX", "getTextbox");


    private final String type;
    private final String collectionType;
    private final String typeGetterMethod;

    AqalityTypes(String type, String collectionType, String typeGetterMethod) {
        this.type = type;
        this.collectionType = collectionType;
        this.typeGetterMethod = typeGetterMethod;
    }


    public String getType() {
        return type;
    }

    public String getListDiamondsType() {
        return "List<" + type + ">";
    }

    public String getCollectionType() {
        return collectionType;
    }

    public String getTypeGetterMethod() {
        return typeGetterMethod;
    }

}
