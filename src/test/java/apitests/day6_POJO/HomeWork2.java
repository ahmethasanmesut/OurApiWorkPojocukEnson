package apitests.day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.ExcelUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

public class HomeWork2 {

    //create one mackaroo api for name,gender,phone
    //send get request to retrieve random info from that api
    //use those info to send post request to spartan

  @BeforeClass
    public void before(){
     baseURI = ConfigurationReader.get("mock_url");
  }

  @Test
    public void test(){
    Random rn = new Random();

    Response response = given().log().all().accept(ContentType.JSON)
            .queryParam("key", "4771caa0")
            .when().get(ConfigurationReader.get("mock_url")+"/hanume.json");
    assertEquals(response.statusCode(),200);
    assertEquals(response.contentType(),"application/json");

    List<Map<String,Object>> mockspartan = response.body().as(List.class);


    Response postresponse = given().log().all().accept(ContentType.JSON)
            .and().contentType(ContentType.JSON)
            .and().body(mockspartan.get(rn.nextInt(mockspartan.size())))
            .when().post(ConfigurationReader.get("spartan_api_url") + "/api/spartans");
    assertEquals(postresponse.statusCode(),201);



  }

  /*
    @Test
          public void test2(){
      Random rc = new Random();
    Response response = given().accept(ContentType.JSON)
            .queryParam("key", "4771caa0")
            .when().get(ConfigurationReader.get("mock_url")+"/hanume.json");

    List<Map<String,Object>> allMockList = response.body().as(List.class);

    Response postResponse = given().log().all()
            .accept(ContentType.JSON)
            .and()
            .contentType(ContentType.JSON)
            .and()
            .body(allMockList.get(rc.nextInt(allMockList.size())))
            .when()
            .post(ConfigurationReader.get("spartan_api_url") + "/api/spartans");
    assertEquals(postResponse.statusCode(),201);

    int idFromPost = postResponse.path("data.id");
    System.out.println("idFromPost = " + idFromPost);

    given().accept(ContentType.JSON)
            .pathParam("id",idFromPost)
            .when().get(ConfigurationReader.get("spartan_api_url")+ "/api/spartans/{id}")
            .then().statusCode(200).log().all();


  }

   */
}
