package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Board;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.CheckResponseIsValid;
import utils.SendRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class First_Test{


    Properties prop = new Properties();
    protected RequestSpecification requestSpec;
    private String SECRET_KEY;


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


    @Test
    public void firstMethod() throws JsonProcessingException {
        //Check that everything works by typing the URL to the address line
        String url = "https://api.trello.com/1/members/me?" + getSecretKey();

        Response response = SendRequest.sendGetRequest(url, requestSpec);
        response.prettyPrint();

        //Verifying the response
        CheckResponseIsValid.checkResponseCode(response, 200);
        CheckResponseIsValid.checkResponseBodyContains(response, "username");


//        // Deserialize response to Board object
        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);



        Board board = objectMapper.readValue(response.asString(), Board.class);
        System.out.println(board.getName());
        System.out.println(board.getUrl());
        System.out.println(board.getId());
        System.out.println(board.getClass());
//
//


    }

}
