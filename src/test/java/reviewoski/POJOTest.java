package reviewoski;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class POJOTest {
String spartanUrl = "http://3.84.52.125:8000";
    String zipUrl = "http://api.zippopotam.us";
    @Test
    public void spartanTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id",7)
                .when().get(spartanUrl+"/api/spartans/{id}");
        response.prettyPrint();


    }
    @Test
    public void zipTestPojo(){

        Response response = given().accept(ContentType.JSON)
                .and().pathParam("zip", 22031)
                .when().get(zipUrl + "/us/{zip}");

        PostCode zip22031 = response.body().as(PostCode.class);
        System.out.println("zip22031.getCountry() = " + zip22031.getCountry());

        // places are stored as list<places> so we need to use index
        String actualLatitude = zip22031.getPlaces().get(0).getLatitude();
        String expectedLatitude = "38.8604";
        Assert.assertEquals(actualLatitude,expectedLatitude);

        Assert.assertEquals(zip22031.getCountryAbbreviation(),"US");



    }
}
