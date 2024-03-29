package apitests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class jsonToJavaCollection {
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test
    public void SpartanToMap(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", "15")
                .when().get("/api/spartans/{id}");

        assertEquals(response.statusCode(),200);

        //we will convert json response to java map
        Map<String,Object> jsonDataMap = response.body().as(Map.class);
        System.out.println("jsonDataMap = " + jsonDataMap);

        String name = (String) jsonDataMap.get("name");
        assertEquals(name,"Meta");

        BigDecimal phone = new BigDecimal(String.valueOf(jsonDataMap.get("phone")));
        System.out.println("phone = " + phone);


    }
    @Test //bugunden
    public void allSpartansList(){

        Response response = given().accept(ContentType.JSON)
                .when().get("/api/spartans");

        assertEquals(response.statusCode(),200);

        //we need to de-serialize JSON response to List of Maps
        List<Map<String, Object>> allSpartanList = response.body().as(List.class);
        System.out.println(allSpartanList);

        //print second spartan first name
        System.out.println(allSpartanList.get(1).get("name"));
         //save spartan3 in a map
        Map<String,Object> spartan3 = allSpartanList.get(2);

        System.out.println(spartan3);

    }

    @Test //bugunden
    public void regiontoMap(){
        Response response = when().get("http://3.84.52.125:1000/ords/hr/regions");
        assertEquals(response.statusCode(),200);

        //we de-serialize JSON response to Map
        Map<String, Object> regionMap = response.body().as(Map.class);

        System.out.println(regionMap.get("count"));

        System.out.println(regionMap.get("hasMore"));

        System.out.println(regionMap.get("items"));

        List<Map<String, Object>> itemList = (List<Map<String, Object>>) regionMap.get("items");

        //print first region name
        System.out.println(itemList.get(0).get("region_name"));

    }
    @Test
    public void asdasd(){
        baseURI = "https://cybertek-reservation-api-qa3.herokuapp.com";
        Map<String,String> params = new HashMap<>();
        params.put("email","sbirdbj@fc2.com");
        params.put("password","asenorval");
        Response response = RestAssured.given().accept(ContentType.JSON).queryParams(params).when().get("/sign");

        Assert.assertEquals(200,response.statusCode());
        String token = response.path("accessToken");
        System.out.println("token = " + token);
        String bearerToken = "Bearer " + token;
        System.out.println("bearerToken = " + bearerToken);

        Response response1 = RestAssured.given().accept(ContentType.JSON)
                .headers("Authorization", bearerToken).when().get("/api/conferences");

        System.out.println("response1.statusCode() = " + response1.statusCode());

        List<Map<String, Object>> listMap = response1.body().as(List.class);
        System.out.println("listMap.get(0).get(\"room\") = " + listMap.get(0).get("room"));
    }

}
