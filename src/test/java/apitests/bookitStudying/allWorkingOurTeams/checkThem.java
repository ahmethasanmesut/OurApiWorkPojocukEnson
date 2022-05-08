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
    @Test
    public void test3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .header("Authorization", finalToken)
                .when().get("/api/teams");
        List<Map<String,Object>> allList = response.as(List.class);
        System.out.println("allList.get(2) = " + allList.get(2));

        List<Map<String,Object>> thirdInfo = (List<Map<String, Object>>) allList.get(2).get("members");
        System.out.println("thirdInfo.get(1).get(\"lastName\") = " + thirdInfo.get(1).get("lastName"));

        System.out.println("allList.get(2).get(\"id\") = " + allList.get(2).get("id"));
           List<Object> firstNames = new ArrayList<>();
        for (int i = 0; i < thirdInfo.size(); i++) {
             firstNames.add(thirdInfo.get(i).get("firstName"));

        }
        System.out.println("firstNames = " + firstNames);
    }


}
