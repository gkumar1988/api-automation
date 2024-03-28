package api;

import framework.ApiTestBase;
import framework.BeforeSuiteBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class CurrencyExchangeTestBase extends BeforeSuiteBase {

    public Response listQuotes() {
        Map<String, String> headers = new HashMap<>();
        headers.put("X-RapidAPI-Host", CurrencyExchangePaths.apiHost);
        headers.put("X-RapidAPI-Key", CurrencyExchangePaths.apiKey);
        return ApiTestBase.doGet(CurrencyExchangePaths.listQuotes, headers, ContentType.JSON);
    }


}
