package apitests.hrStudying.hr;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import utilities.ConfigurationReader;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class hrAttraction {

    @BeforeClass
    public static void setUp(){
        baseURI = ConfigurationReader.get("hr_api_url");

    }
    @Test
    public void test1(){
        Response response = RestAssured.given().log().all().accept(ContentType.JSON)
                .when().log().all().get("/regions");

        AllRego allRego = response.body().as(AllRego.class);
        System.out.println("allRego.getLimit() = " + allRego.getLimit());
        String eu = allRego.getItems().get(0).getregion_name();
        System.out.println("eu = " + eu);

        List<Item> link = allRego.getItems();
        for (int i = 0; i < link.size(); i++) {

            String href = allRego.getItems().get(i).getLinks().get(0).getHref();
            String[] str = href.split("/");
            System.out.println("Arrays.toString(str) = " + Arrays.toString(str));
            int id = Integer.parseInt(str[str.length - 1]);
            System.out.println("id = " + id);
            int regionId = allRego.getItems().get(i).getregion_id();

            Assert.assertEquals("Not True",id,regionId);

        }
        /*
        String href = allRego.getItems().get(1).getLinks().get(0).getHref();
        String[] str = href.split("/");
        System.out.println("Arrays.toString(str) = " + Arrays.toString(str));
        int id = Integer.parseInt(str[str.length - 1]);
        int regionId = allRego.getItems().get(1).getregion_id();

        Assert.assertEquals("Not True",id,regionId);

         */

        Assert.assertEquals(response.statusCode(),200);
    }
    @Test
    public void test2(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("/regions");
        AllRego allRego = response.body().as(AllRego.class);
        String Actrel = allRego.getLinks().get(2).getRel();
        System.out.println("Actrel = " + Actrel);
        String ExRel = "describedby";
        System.out.println("ExRel = " + ExRel);

        Assert.assertTrue(ExRel.equals(Actrel));

    }

}
