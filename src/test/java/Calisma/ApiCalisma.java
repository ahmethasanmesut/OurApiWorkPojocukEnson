package Calisma;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

import Calisma.Calisma.SpartanPost;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.annotations.BeforeClass;
import utilities.ConfigurationReader;

public class ApiCalisma {
    String spartanUrl = "http://3.84.52.125:8000";


    @Test
public void test1(){
        given().accept(ContentType.JSON).when().get(spartanUrl+"/api/spartans")
                .then().assertThat().statusCode(200).and().assertThat().contentType(ContentType.JSON);

    }
    @Test
    public void test2(){
        SpartanPost spartanPost = new SpartanPost();
             given().accept(ContentType.JSON).and().contentType(ContentType.JSON)
                        .body(spartanPost).when().post(spartanUrl + "/api/spartans");

    }
}
