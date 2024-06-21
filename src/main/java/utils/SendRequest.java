package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class SendRequest {
    public static Response sendPostRequest(String url, RequestSpecification spec) {
        return given().spec(spec).post(url);
    }

    public static Response sendGetRequest(String url, RequestSpecification spec) {
        return given().spec(spec).get(url);
    }

    public static Response sendPutRequest(String url, RequestSpecification spec) {
        return given().spec(spec).put(url);
    }

    public static Response sendDeleteRequest(String url, RequestSpecification spec) {
        return given().spec(spec).delete(url);
    }
}
