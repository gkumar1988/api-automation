package framework;

import com.example.apiautomation.model.RunnerVariables;
import com.example.apiautomation.service.CustomThreadLocal;
import org.testng.annotations.BeforeSuite;

import java.util.Properties;

public class BeforeSuiteBase {

    protected static Properties suiteProperties = new Properties();

    @BeforeSuite(alwaysRun = true)
    public static void loadEnvironment() {
        //load common properties
        Properties commonProperties = new TestPropertiesLoader().loadProperties(SuiteVariables.COMMON_PROPERTIES.getValue());

        /*
         * find out which environment to run tests on
         * if environment is supplied by the runner API, use that
         * else
         * use default value mentioned in common.properties
         * AND
         * override value of environment in common.properties from test runner
         */

        String testEnvironment = testEnvironmentDecider(commonProperties);
        commonProperties.setProperty(SuiteVariables.ENVIRONMENT.getValue(), testEnvironment);

        //load test environment properties
        Properties environmentProperties = new TestPropertiesLoader().loadProperties(testEnvironment);
        mergeProperties(commonProperties, environmentProperties);
    }

    private static String testEnvironmentDecider(Properties commonProperties) {

        String environmentFromRunner = getEnvironmentFromAPIRunner();
        if (environmentFromRunner != null) {
            return environmentFromRunner;
        } else {
            return commonProperties.getProperty(SuiteVariables.ENVIRONMENT.getValue());
        }
    }

    private static String getEnvironmentFromAPIRunner() {
        return CustomThreadLocal.getValue(RunnerVariables.ENVIRONMENT.getValue());
    }

    private static void mergeProperties(Properties... properties) {
        for (Properties prop : properties) {
            if (prop != null) {
                suiteProperties.putAll(prop);
            }
        }
    }
}
