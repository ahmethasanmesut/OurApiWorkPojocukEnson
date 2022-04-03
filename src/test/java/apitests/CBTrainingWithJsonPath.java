package apitests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class CBTrainingWithJsonPath {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("cbt_api_url");
    }

    @Test
    public void test1(){
        Response response = given().accept(ContentType.JSON)
                .and().pathParam("id", "29702")
                .when().get("/student/{id}");

        assertEquals(response.statusCode(),200);

        JsonPath jsonPath =response.jsonPath();

        String firstName = jsonPath.getString("students.firstName[0]");
        System.out.println("firstName = " + firstName);

        String lastName = jsonPath.get("students.lastName[0]");
        System.out.println("lastName = " + lastName);

        String phone = jsonPath.getString("students.contact[0].phone");
        System.out.println("phone = " + phone);

        //get me city and zipcode, do assertion
        String city = jsonPath.getString("students.company[0].address.city");
        System.out.println("city = " + city);
        assertEquals(city,"Chicago");

        int zipCode = jsonPath.getInt("students.company[0].address.zipCode");
        System.out.println("zipCode = " + zipCode);
        assertEquals(zipCode,60606);

        String firstName2 = jsonPath.getString("students.firstName");
        System.out.println("firstName2 = " + firstName2);

        //String firstname3 =response.path("students.firstName");
        //System.out.println("firstname3 = " + firstname3);

        String zipCode2 = response.path("students.company[0].address.zipCode").toString();
        System.out.println("zipCode2 = " + zipCode2);
    }
}
