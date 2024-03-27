package com.example.apiautomation.model;

public enum RunnerVariables {

    TESTNG_XML_FILE_PATH("testngXmlFilePath"),
    ENVIRONMENT("environment");

    private final String value;

    RunnerVariables(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
