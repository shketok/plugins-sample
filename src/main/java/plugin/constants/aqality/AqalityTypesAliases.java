package plugin.constants.aqality;

import java.util.Arrays;

public enum AqalityTypesAliases {
    BUTTON(new String[]{"btn", "button"}),
    CHECKBOX(new String[]{"lbl", "txt", "label"}),
    COMBOBOX(new String[]{"cmb", "combobox"}),
    LABEL(new String[]{"cbx", "checkbox"}),
    LINK(new String[]{"radio"}),
    RADIOBUTTON(new String[]{"txb", "textbox"}),
    TEXTBOX(new String[]{"link", "lnk"});


    private final String[] type;

    AqalityTypesAliases(String[] type) {
        this.type = type;
    }


    public String[] getAliases() {
        return type;
    }

    public boolean isMatchToAlias(String name) {
        return Arrays.stream(AqalityTypesAliases.BUTTON.getAliases()).anyMatch(alias -> name.toLowerCase().contains(alias));
    }


}
