package framework;

public enum SuiteVariables {

    COMMON_PROPERTIES("common"),
    ENVIRONMENT("environment");

    private final String value;

    SuiteVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
