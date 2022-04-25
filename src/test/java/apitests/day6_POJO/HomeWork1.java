package apitests.day6_POJO;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;
import utilities.ExcelUtil;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.*;

//1-Create csv file from mackaroo website, which includes name,gender,phone
//2-Download excel file
//3- using testng data provider and apache poi create data driven posting from spartan


public class HomeWork1 {

    @BeforeClass
    public void beforeclass() {

        baseURI = ConfigurationReader.get("spartan_api_url");

    }

    @DataProvider
    public Object[][] userData() {

        ExcelUtil spartanData = new ExcelUtil("src/test/resources/Spartan_Data.xlsx","data");

        return spartanData.getDataArrayWithoutFirstRow();
    }
    @Test(dataProvider = "userData")
    public void test1(String name, String gender, String phone){

        Spartan spartan = new Spartan();
        spartan.setName(name);
        spartan.setGender(gender);
        BigDecimal phone1 = new BigDecimal(phone);
        spartan.setPhone(phone1.longValue());

        given().log().all().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .header("transfer-encoding","chunked")
                .log().all();
        /*
        Map<String,Object> requestMap=new HashMap<>();

        BigDecimal phone1=new BigDecimal(phone);


        requestMap.put("name",name);
        requestMap.put("gender",gender);
        requestMap.put("phone",phone1.longValue());


        given().log().all().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestMap)
                .when().post("/api/spartans")
                .then().statusCode(201)
                .contentType("application/json")
                .header("transfer-encoding","chunked")
                .log().all();

         */



    }





}
    
