package Calisma;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiCalisma {


    @BeforeClass
    public  void beforeclass(){

        baseURI= ConfigurationReader.get("spartan_api_url");
    }


 @Test
public void test1(){

      given().accept(ContentType.JSON).when().get("/api/spartans").then()
              .assertThat().statusCode(Matchers.equalTo(200));



  }
    @Test
    public void test2(){
        Map<String,Object> myBody = new HashMap<>();
        myBody.put("gender","Female");
        myBody.put("name","suzan");
        myBody.put("phone",97453623456L);

//            String myBody ="{\n" +
//                    "  \"gender\": \"Female\",\n" +
//                    "  \"name\": \"Janett\",\n" +
//                    "  \"phone\": 123459876789\n" +
//                    "}";

        given().contentType(ContentType.JSON)
                .body(myBody).when().post( "/api/spartans")
                .then().assertThat().statusCode(201)
                .log().all();

    }
    @Test
    public void test3(){

        String myBody = "{\n" +
                "    \"name\": \"suzan\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 97453623456\n" +
                "}";
        String patBody = "{\n" +
                "  \"gender\": \"Male\"\n" +
                "\n" +
                "}";

        given().contentType(ContentType.JSON).body(myBody)
                .pathParam("id",289).when().put("/api/spartans/{id}")
                .then().assertThat().statusCode(204).log().all();

        given().accept(ContentType.JSON).when().get("/api/spartans/289").then().log().all();

        given().contentType(ContentType.JSON).body(patBody).pathParam("id",289)
                .when().patch("/api/spartans/{id}").then().assertThat().statusCode(204).log().all();

        given().accept(ContentType.JSON).when().get("/api/spartans/289").then().log().all();

    }

   @Test
    public void test4(){
        given().accept(ContentType.JSON).when().get("/api/spartans")
                .then().assertThat().statusCode(200).and().assertThat().contentType(ContentType.JSON)
                .assertThat().body("id[3]",Matchers.equalTo(289));
    }
    @Test
    public void test5(){
        Response response = get("/api/spartans");
        JsonPath json = response.jsonPath();
        List<Object> name = json.getList("name");
        System.out.println(name);
        String stringName1 = json.getString("name[1]");
        System.out.println(stringName1);
        long phone = json.getLong("phone[1]");
        System.out.println(phone);
    }
    @Test
    public void test6(){
        String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNDAiLCJhdWQiOiJzdHVkZW50LXRlYW0tbGVhZGVyIn0.xNvdQRyrYMb3Ec5QByHwXowBo3zPK2jQQS1eJ2RYIto";
        Response response = given().accept(ContentType.JSON).headers("Authorization", accessToken).when()
                .get("https://cybertek-reservation-api-qa3.herokuapp.com/api/conferences");
        //response.prettyPrint();

       List<String> rooms = response.path("room.name");
        System.out.println("rooms = " + rooms);

    }

}
