package apitests.day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;


public class PostRequestDemo {   //day7

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
     /*
    Given accept type and Content type is JSON
    And request json body is:
    {
      "gender":"Male",
      "name":"MikeEU",
      "phone":8877445596
   }
    When user sends POST request to '/api/spartans'
    Then status code 201
    And content type should be application/json
    And json payload/response/body should contain:
    "A Spartan is Born!" message
    and same data what is posted
 */
    @Test
    public void PostNewSpartan(){
        String jsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"MikeSmith\",\n" +
                "  \"phone\": 1237843291\n" +
                "}";
        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(jsonBody)
                .when()
                .post("/api/spartans");

        response.prettyPrint();
        //verify status code 201 for post
        assertEquals(response.statusCode(),201);

        //verify content type
        assertEquals(response.contentType(),"application/json");

        //verify successful message
        String actualMessage = response.path("success");
        String expectedMessage = "A Spartan is Born!";

        assertEquals(actualMessage,expectedMessage);

        //short way
        //assertEquals(response.path("success"),"A Spartan is Born!");

        //assertion for spartan data
        String name = response.path("data.name");
        String gender = response.path("data.gender");
        long phone = response.path("data.phone");

        assertEquals(name,"MikeSmith");
        assertEquals(gender,"Male");
        assertEquals(phone,8877445596l);
    }
    @Test
    public void PostNewSpartan2(){
        Map<String,Object>requestMap = new HashMap<>();

        requestMap.put("name","MikeSmith");
        requestMap.put("gender","Male");
        requestMap.put("phone",8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(requestMap)
                .when()
                .post("/api/spartans")
                .then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"),
                "data.name",equalTo("MikeSmith"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(8877445596l));

    }
    @Test
    public void PostNewSpartan3(){

        Response response = given().accept(ContentType.JSON)
                .when().get("mackarooapilink");
       String randomName =  response.path("name");

        Spartan spartaneu = new Spartan();

        spartaneu.setName("MikeSmith");
        spartaneu.setGender("Male");
        spartaneu.setPhone(8877445596l);

        given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartaneu)
                .when()
                .post("/api/spartans")
                .then().log().all()
                .statusCode(201)
                .and()
                .contentType("application/json")
                .body("success", is("A Spartan is Born!"),
                        "data.name",equalTo("MikeSmith"),
                        "data.gender",equalTo("Male"),
                        "data.phone",equalTo(8877445596l));

        //after post request, send a get request to generated spartan

    }
    @Test
    public void PostNewSpartan4(){

        Spartan spartaneu = new Spartan();

        spartaneu.setName("MikeSmith");
        spartaneu.setGender("Male");
        spartaneu.setPhone(8877445596l);

        Response response = given().log().all()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(spartaneu)
                .when()
                .post("/api/spartans");

        //END OF POST REQUEST


        //get request
        int id = response.path("data.id");
        System.out.println("id = " + id);
        //after post request, send a get request to generated spartan
        given().accept(ContentType.JSON)
                .pathParam("id",id)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).log().all();


    }
}