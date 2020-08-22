package testrail;

public enum Urls {
    GET_CASE("/get_case/%1$s"),
    UPDATE_CASE("/update_case/%1$s");

    private String urlPart;

    Urls(String urlPart) {
        this.urlPart = urlPart;
    }


    public String getUrlPart(Object... args) {
        return String.format(urlPart, args);
    }
}
