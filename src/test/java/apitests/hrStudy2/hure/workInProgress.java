package apitests.hrStudy2.hure;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utilities.ConfigurationReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;

public class workInProgress {
    @BeforeClass
    public static void setUp(){
        baseURI = ConfigurationReader.get("hr_api_url");
    }
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/employees");
        PojoListKarisik pojoList = response.body().as(PojoListKarisik.class);
        String hrefAct = pojoList.getItems().get(5).getLinks().get(0).getHref();
        System.out.println("hrefAct = " + hrefAct);
        String hrefExp = "http://3.84.52.125:1000/ords/hr/employees/105";
        Assert.assertEquals(hrefExp,hrefAct);

        String href = pojoList.getLinks().get(4).getHref();
        String[] splitHref = href.split("\\?");
        System.out.println("Arrays.toString(splitHref) = " + Arrays.toString(splitHref));
        String[] splitNumber = splitHref[1].split("=");
        int actNumber = Integer.parseInt(splitNumber[1]);
        System.out.println("actNumber = " + actNumber);
        int expNumber = 25;
        Assert.assertEquals(expNumber,actNumber);

    }
    @Test
    public void testListAndMap(){


    }
}
