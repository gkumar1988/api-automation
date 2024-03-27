package com.example.apiautomation.model;

public class TestCase {

    private String testCaseName;
    private String testCaseDescription;
    private Boolean isEnabled;
    private String testCaseLink;
    private String methodName;


    private TestCaseStatus testCaseStatus;

    public TestCase(String testCaseName, String testCaseDescription, Boolean isEnabled, String testCaseLink, String methodName, TestCaseStatus testCaseStatus) {
        this.testCaseName = testCaseName;
        this.testCaseDescription = testCaseDescription;
        this.isEnabled = isEnabled;
        this.testCaseLink = testCaseLink;
        this.methodName = methodName;
        this.testCaseStatus = testCaseStatus;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public TestCaseStatus getTestCaseStatus() {
        return testCaseStatus;
    }

    public void setTestCaseStatus(TestCaseStatus testCaseStatus) {
        this.testCaseStatus = testCaseStatus;
    }
}
