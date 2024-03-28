package api;

import framework.BeforeSuiteBase;

public class CurrencyExchangePaths extends BeforeSuiteBase {

    private static final String currencyExchangeBasePath = suiteProperties.getProperty("com.service.currencyExchange");

    public static String listQuotes = currencyExchangeBasePath + "/listquotes";

    public static String apiHost = currencyExchangeBasePath.substring(currencyExchangeBasePath.indexOf(":") + 3);
    public static String apiKey = "SIGN-UP-FOR-KEY";


}
