import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class firstTest {
    @Test
    public void firstMethod(){
        System.out.println("crazy");

        given().
                get("https://api.trello.com/1/members/me?token=ATTAcc18fb6e2733f72364c15a045d15f4b21c42ea810c9f954668743ea9ca5404f471EF68F5&key=d611d2471d28747c4546167ec8731e87")
                .prettyPrint();



    }
}
