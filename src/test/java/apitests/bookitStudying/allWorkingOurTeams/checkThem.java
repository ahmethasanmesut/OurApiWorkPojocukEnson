package apitests.bookitStudying.allWorkingOurTeams;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class checkThem {
   public static String finalToken;
    @BeforeClass
    public static void setUp(){
        baseURI = ConfigurationReader.get("bookit_url");
        Response response = RestAssured.given().queryParams("email", "sbirdbj@fc2.com",
                        "password", "asenorval").accept(ContentType.JSON).when()
                .get("/sign");
        String accessToken = (String) response.path("accessToken");
        finalToken = "Bearer " + accessToken;

    }
    @Test
    public void test1(){
        System.out.println("finalToken = " + finalToken);

        Response response = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", finalToken)
                .when().get("/api/teams/48");
        AllTeam allteam = response.body().as(AllTeam.class);
        System.out.println("allteam.getMembers().get(2).getLastName() = " + allteam.getMembers().get(2).getLastName());

    }
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", finalToken)
                .when().get("/api/teams");
        List<Map<String,Object>> generalList = response.body().as(List.class);
        System.out.println("generalList.get(0) = " + generalList.get(0));

        List<Map<String,Object>> firstInfo = (List<Map<String, Object>>) generalList.get(0).get("members");
        System.out.println("firstInfo.get(0).get(\"firstName\") = " + firstInfo.get(0).get("firstName"));
        System.out.println("firstInfo = " + firstInfo);

        System.out.println("generalList.get(0).get(\"id\") = " + generalList.get(0).get("id"));
        List<Object> names = new ArrayList<>();
        for (int i = 0; i < generalList.size(); i++) {
            names.add(generalList.get(i).get("name")) ;
        }
        System.out.println("names = " + names);

    }


}
