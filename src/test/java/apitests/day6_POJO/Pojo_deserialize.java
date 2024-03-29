package apitests.day6_POJO;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;



public class Pojo_deserialize {

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://3.84.52.125:8000/api/spartans/{id}");
        assertEquals(response.statusCode(),200);

        //JSON to POJO --> de serialize to java custom class
        //json to our spartan class obj

        Spartan spartan15 = response.body().as(Spartan.class);

        System.out.println(spartan15);

        System.out.println("spartan15.getName() = " + spartan15.getName());
        System.out.println("spartan15.getPhone() = " + spartan15.getPhone());

        assertEquals(spartan15.getId(),15);
        assertEquals(spartan15.getName(),"Meta");


    }
    @Test
    public void regionToPojo(){
        Response response = when().get("http://3.84.52.125:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //JSON to POJO(regions class)
        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getHasMore() = " + regions.getHasMore());
        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());

        List<Item> items = regions.getItems();

        System.out.println(items.get(1).getRegionId());
    }
    @Test
    public void gson_example(){

        Gson gson = new Gson();

        //JSON to JAva collections or Pojo --> De-serialization
        String myJsonData = "{\n" +
                "    \"id\": 15,\n" +
                "    \"name\": \"Meta\",\n" +
                "    \"gender\": \"Female\",\n" +
                "    \"phone\": 1938695106\n" +
                "}";

        Map <String, Object> map = gson.fromJson(myJsonData, Map.class);
        System.out.println(map);

        Spartan spartan15 = gson.fromJson(myJsonData, Spartan.class);
        System.out.println(spartan15);

        //-----------SERIALIZATION---------------
        //JAVA Collection or POJO to JSON

        Spartan spartaneu = new Spartan(200,"Mike","male",12123123);

        String jsonspartaneu = gson.toJson(spartaneu);
        System.out.println("jsonspartaneu = " + jsonspartaneu);

    }
}
