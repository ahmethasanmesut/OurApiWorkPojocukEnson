package apitests.day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class Hazir1 {

    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    /*
   Given accept type and Content type is JSON
   And request json body is:
   {
     "gender":"Male",
     "name":"George Hagi",
     "phone":3584347819
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
        String myJson = "{\n" +
                "    \"name\": \"George Hagi\",\n" +
                "    \"gender\": \"Male\",\n" +
                "    \"phone\": 3584347819\n" +
                "    }";

        Response response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(myJson)
                .when().post("/api/spartans");

        response.prettyPrint();

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json");

        assertEquals(response.path("success"), "A Spartan is Born!");

      String name =   response.path("data.name");
       String gender =  response.path("data.gender");
      long phone = response.path("data.phone");

        System.out.println(name);
        System.out.println(gender);
        System.out.println(phone);




    }
    @Test
    public void PostNewSpartan2(){
     Map<String,Object> map = new HashMap<>();



    }

}
