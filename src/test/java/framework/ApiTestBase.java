package framework;

import framework.restassuredcore.HttpRestMethod;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiTestBase {

    public static Response doPost(String url, String body, String token, ContentType contentType) {
        return doRequest(Method.POST,url,body,token, contentType != null ? contentType : ContentType.JSON);
    }

    public static Response doRequest(Method method, String url, String body, String token, ContentType contentType) {

        Map<String, String> headers = new HashMap<>();
        if (token != null) {
            headers.put("Authorization", token);
        }

        return RestAssured.given()
                .log().all()
                .contentType(contentType)
                .headers(headers)
                .body(body)
                .when()
                .request(method,url)
                .then()
                .log().all()
                .extract().response();
    }
}
