package utils;


import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BuildRequest {
    public static RequestSpecification buildPostRequest(RequestSpecification spec, Object body) {
        return given().spec(spec).body(body);
    }

    public static RequestSpecification buildGetRequest(RequestSpecification spec) {
        return given().spec(spec);
    }

    public static RequestSpecification buildPutRequest(RequestSpecification spec, Object body) {
        return given().spec(spec).body(body);
    }

    public static RequestSpecification buildDeleteRequest(RequestSpecification spec) {
        return given().spec(spec);
    }
}
