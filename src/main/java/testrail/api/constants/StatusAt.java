package testrail.api.constants;

public enum  StatusAt {
    DONE("custom_status_at", 3L);

    private String fieldName;
    private Long fieldValue;

    StatusAt(String fieldName, Long fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Long getFieldValue() {
        return fieldValue;
    }
}
