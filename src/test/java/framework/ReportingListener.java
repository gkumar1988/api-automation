package framework;

import com.example.apiautomation.model.TestCase;
import com.example.apiautomation.model.TestCaseStatus;
import com.example.apiautomation.model.TestSuite;
import com.example.apiautomation.model.TestSuiteStatus;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReportingListener implements ITestListener {

    private static String SUITE_NAME;
    private static LocalDateTime SUITE_START_TIME;
    private static LocalDateTime SUITE_END_TIME;
    private static long SUITE_RUN_DURATION;
    private static final String TEST_CASE_LINK_FIELD_NAME = "testCaseLink";
    private List<TestCase> testCasesListWithStatus = new ArrayList<>();

    @Override
    public void onTestStart(ITestResult result) {
        SUITE_START_TIME = LocalDateTime.now();

        Test annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testCaseName = annotation.testName();
        String description = annotation.description();
        boolean enabled = annotation.enabled();
        String testCaseLink = getTestCaseLink(result);
        String methodName = result.getMethod().getMethodName();
        TestCaseStatus status = TestCaseStatus.FAIL;

        testCasesListWithStatus.add(new TestCase(testCaseName, description, enabled, testCaseLink, methodName, status));
    }

    @Override
    public void onFinish(ITestContext context) {
        SUITE_END_TIME = LocalDateTime.now();
        SUITE_NAME = context.getSuite().getName();

        Duration d = Duration.between(SUITE_START_TIME, SUITE_END_TIME);
        SUITE_RUN_DURATION = d.getNano();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        updateTestStatus(result, TestCaseStatus.PASS);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        updateTestStatus(result, TestCaseStatus.FAIL);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        updateTestStatus(result, TestCaseStatus.SKIPPED);
    }

    private String getTestCaseLink(ITestResult result) {
        Test annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        String testCaseLink = "";
        if (annotation.attributes().length >= 1) {
            return Arrays.stream(annotation.attributes()).filter(customAttribute ->
                            customAttribute.name().equals(TEST_CASE_LINK_FIELD_NAME))
                    .collect(Collectors.toList()).get(0).values()[0];
        }
        return testCaseLink;
    }

    private synchronized void updateTestStatus(ITestResult result, TestCaseStatus testCaseStatus) {
        Test annotation = result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(Test.class);
        for (TestCase testCase : testCasesListWithStatus) {
            if (testCase.getTestCaseName().equals(annotation.testName())) {
                testCase.setTestCaseStatus(testCaseStatus);
            }
        }
    }

    public TestSuite getTestSuiteResults() {
        long totalPassedTests;
        long totalFailedTests;
        long totalSkippedTests;

        totalPassedTests = testCasesListWithStatus.stream().filter(testCase ->
                testCase.getTestCaseStatus().equals(TestCaseStatus.PASS)).count();
        totalFailedTests = testCasesListWithStatus.stream().filter(testCase ->
                testCase.getTestCaseStatus().equals(TestCaseStatus.FAIL)).count();
        totalSkippedTests = testCasesListWithStatus.stream().filter(testCase ->
                testCase.getTestCaseStatus().equals(TestCaseStatus.SKIPPED)).count();

        TestSuiteStatus suiteStatus = (totalFailedTests == 0 && totalSkippedTests == 0) ? TestSuiteStatus.PASS : TestSuiteStatus.FAIL;
        long totalTestsInSuite = totalPassedTests + totalFailedTests + totalSkippedTests;

        return new TestSuite(SUITE_NAME, SUITE_START_TIME, SUITE_END_TIME, SUITE_RUN_DURATION, suiteStatus, totalTestsInSuite, testCasesListWithStatus);
    }
}
