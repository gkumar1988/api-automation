package com.example.apiautomation.service;

import com.example.apiautomation.model.RunTestRequestBody;
import com.example.apiautomation.model.RunnerVariables;
import com.example.apiautomation.model.TestSuite;
import framework.ReportingListener;
import org.springframework.stereotype.Service;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestNGRunnerImpl {

    public TestSuite runTests(RunTestRequestBody requestBody) {

        TestNG testNG = new TestNG();

        System.setProperty(RunnerVariables.TESTNG_XML_FILE_PATH.getValue(), requestBody.getXmlFile());

        List<String> testSuites = new ArrayList<>();
        testSuites.add(System.getProperty(RunnerVariables.TESTNG_XML_FILE_PATH.getValue()));
        testNG.setTestSuites(testSuites);

        ReportingListener reportingListener = new ReportingListener();
        testNG.addListener(reportingListener);
        CustomThreadLocal.setVariables(RunnerVariables.ENVIRONMENT.getValue(), requestBody.getEnvironment());
        testNG.run();

        return reportingListener.getTestSuiteResults();
    }
}
