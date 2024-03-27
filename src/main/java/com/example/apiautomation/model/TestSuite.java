package com.example.apiautomation.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "testSuiteDoc")
public class TestSuite {

    @Id
    private String id;

    private String suiteName;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private long executionTime;

    private TestSuiteStatus suiteStatus;

    private long totalTests;

    private List<TestCase> testCases;

    public TestSuite(String suiteName, LocalDateTime startTime, LocalDateTime endTime, long executionTime, TestSuiteStatus suiteStatus, long totalTests, List<TestCase> testCases) {
        this.suiteName = suiteName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.executionTime = executionTime;
        this.suiteStatus = suiteStatus;
        this.totalTests = totalTests;
        this.testCases = testCases;
    }

}
