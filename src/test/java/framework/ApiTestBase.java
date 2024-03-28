package framework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class ApiTestBase {

    public static Response doPost(String url, String body, String token, ContentType contentType) {

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
                .request(Method.POST, url)
                .then()
                .log().all()
                .extract().response();
    }

    public static Response doGet(String url, Map<String, String> headers, ContentType contentType) {

        return RestAssured.given()
                .log().all()
                .contentType(contentType)
                .headers(headers)
                .when()
                .request(Method.GET, url)
                .then()
                .log().all()
                .extract().response();
    }

}
