package apitests.bookitStudying.bookit;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;

public class kontrolEtme {
     String finalToken;
    @BeforeClass
    public void beforeClass(){
       baseURI = ConfigurationReader.get("bookit_url");
        //String a = "https://cybertek-reservation-api-qa3.herokuapp.com/sign";
        Response tokenJson = RestAssured.given().queryParams("email","sbirdbj@fc2.com",
                "password","asenorval").accept(ContentType.JSON)
                .when().get("/sign");
           String token = tokenJson.path("accessToken");
           finalToken = "Bearer " + token;
    }

    @Test
    public void test1(){
        System.out.println("finalToken = " + finalToken);
        Response response = given().accept(ContentType.JSON).header("Authorization", finalToken)
                .pathParam("id",36)
                .when().get("/api/teams/{id}");
        BugBusters bugBusters = response.body().as(BugBusters.class);
        System.out.println("bugBusters.getId() = " + bugBusters.getId());

        String firstName = bugBusters.getMembers().get(0).getFirstName();
        System.out.println("firstName = " + firstName);

        String actualLastName = bugBusters.getMembers().get(1).getLastName();
        System.out.println("actualLastName = " + actualLastName);
        String expectedLastName = "Bubble";
        Assert.assertEquals(actualLastName,expectedLastName,"That is not cool");


    }
}
