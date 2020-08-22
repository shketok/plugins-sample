package plugin.aqality;

import plugin.constants.aqality.AqalityTypes;
import plugin.constants.aqality.AqalityTypesAliases;

public class AqalityTypeFactory {

    private AqalityTypeFactory() {}

    public static AqalityTypes getType(final String variableName) {
        if (AqalityTypesAliases.BUTTON.isMatchToAlias(variableName)) {
            return AqalityTypes.BUTTON;
        } else if (AqalityTypesAliases.LABEL.isMatchToAlias(variableName)) {
            return AqalityTypes.LABEL;
        } else if (AqalityTypesAliases.COMBOBOX.isMatchToAlias(variableName)) {
            return AqalityTypes.COMBOBOX;
        } else if (AqalityTypesAliases.CHECKBOX.isMatchToAlias(variableName)) {
            return AqalityTypes.CHECKBOX;
        } else if (AqalityTypesAliases.RADIOBUTTON.isMatchToAlias(variableName)) {
            return AqalityTypes.RADIOBUTTON;
        } else if (AqalityTypesAliases.TEXTBOX.isMatchToAlias(variableName)) {
            return AqalityTypes.TEXTBOX;
        } else if (AqalityTypesAliases.LINK.isMatchToAlias(variableName)) {
            return AqalityTypes.LINK;
        } else {
            return AqalityTypes.LABEL;

        }
    }
}
