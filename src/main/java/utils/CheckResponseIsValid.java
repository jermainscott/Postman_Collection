package utils;


import io.restassured.response.Response;
import models.Board;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



public class CheckResponseIsValid {

    public static void checkResponseCode(Response response, int expectedCode) {
        assertThat(response.getStatusCode()).as("Check response status code").isEqualTo(expectedCode);
    }

    public static void checkResponseBodyContains(Response response, String expectedContent) {
        assertThat(response.getBody().asString()).as("Check response body contains").contains(expectedContent);
    }

    public static void checkBoardFields(Response response, String expectedName) {
        assertThat(response.jsonPath().getString("name")).as("Check board name").isEqualTo(expectedName);
        assertThat(response.jsonPath().getString("id")).as("Check board ID").isNotNull();
    }


}
