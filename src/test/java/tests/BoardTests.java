package tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import models.Board;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.response.Response;
import utils.BuildRequest;
import utils.CheckResponseIsValid;
import utils.SendRequest;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BoardTests{

    Properties prop = new Properties();
    protected RequestSpecification requestSpec;
    private String SECRET_KEY;
    private String boardId;


    @BeforeTest
    public void beforeAllTests() throws IOException {

        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("secrets.properties");
        prop.load(stream);
        String API_KEY = prop.get("api_key").toString();
        String TOKEN = prop.get("token").toString();
        SECRET_KEY = "token=" + TOKEN + "&key=" + API_KEY;

        requestSpec = new RequestSpecBuilder()
                .addQueryParam("key", API_KEY)
                .addQueryParam("token", TOKEN)
                .setContentType("application/json")
                .build();
    }

    public String getSecretKey() {
        return SECRET_KEY;
    }


//Creating the board (POST)
    @Test
    public void createBoardTest() {
        String boardName = "testBoard10";

        Response response = given(requestSpec)
                .queryParam("name", boardName)
                .post("https://api.trello.com/1/boards/")
                .then()
                .extract()
                .response();

        CheckResponseIsValid.checkResponseCode(response, 200);
        CheckResponseIsValid.checkResponseBodyContains(response, boardName);
        CheckResponseIsValid.checkBoardFields(response, boardName);

        // Capture the created board ID for further operations
        boardId = response.jsonPath().getString("id");

        // Print the boardId to ensure it's correct
        System.out.println("Created board ID: " + boardId);
    }



   //Get board by ID (GET)
  @Test(dependsOnMethods = {"createBoardTest"})
    public void getBoardByIdTest() {

        // Print the boardId to ensure it's correct before making the request
        System.out.println("Getting board with ID: " + boardId);


       Response response = given(requestSpec)
               .get("https://api.trello.com/1/boards/" + boardId)
               .then()
               .extract()
               .response();

        CheckResponseIsValid.checkResponseCode(response, 200);
        CheckResponseIsValid.checkResponseBodyContains(response, boardId);
    }



    //Updating board by ID (PUT)
    @Test(dependsOnMethods = {"getBoardByIdTest"})
    public void updateBoardTest() {

        String updatedName = "Updated_TestBoard";

        Response response = given(requestSpec)
                .queryParam("name", updatedName)
                .put("https://api.trello.com/1/boards/" + boardId)
                .then()
                .extract()
                .response();

        CheckResponseIsValid.checkResponseCode(response, 200);
        CheckResponseIsValid.checkResponseBodyContains(response, updatedName);
        CheckResponseIsValid.checkBoardFields(response, updatedName);
    }



    @Test(dependsOnMethods = {"updateBoardTest"})
    public void deleteBoardTest() {

        Response response = given(requestSpec)
                .delete("https://api.trello.com/1/boards/" + boardId)
                .then()
                .extract()
                .response();

        CheckResponseIsValid.checkResponseCode(response, 200);

        // Optionally, reset the boardId to indicate the board has been deleted
        boardId = null;

    }




}
