package apitests.day8;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpartanBasicAuth {

    @Test
    public void test1(){
        given().accept(ContentType.JSON)
                .and().auth().basic("admin","admin")
                .when().get("http://3.84.52.125:8000/api/spartans")
                .then().log().all()
                .statusCode(200);
    }

}
