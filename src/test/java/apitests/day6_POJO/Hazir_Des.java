package apitests.day6_POJO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import jdbctests.Hazir;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

public class Hazir_Des {
    /*
    {
        "id": 15,
        "name": "Meta",
        "gender": "Female",
        "phone": 1938695106
    }
 */

    @Test
    public void oneSpartanPojo(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("http://3.84.52.125:8000/api/spartans/{id}");
        assertEquals(response.statusCode(),200);

        //JSON to POJO --> de serialize to java custom class
        //json to our Hazir class obj

        Hazir hazir15 = response.body().as(Hazir.class);

        System.out.println("hazir15.getName() = " + hazir15.getName());
        System.out.println("hazir15.getGender() = " + hazir15.getGender());
        System.out.println("hazir15.getPhone() = " + hazir15.getPhone());

        assertEquals(hazir15.getName(),"Meta");
        assertEquals(hazir15.getGender(),"Female");
        assertEquals(hazir15.getPhone(),1938695106l);


    }
    @Test
    public void regionToPojo(){
        Response response = when().get("http://3.84.52.125:1000/ords/hr/regions");

        assertEquals(response.statusCode(),200);

        //JSON to POJO(regions class)
        Regions regions = response.body().as(Regions.class);

        System.out.println("regions.getCount() = " + regions.getCount());

        System.out.println(regions.getItems().get(0).getRegionName());
        System.out.println(regions.getItems().get(0).getLinks().get(0).getHref());

        List<Item> items = regions.getItems();

        items.get(0).setRegionName("Russia");
        System.out.println(items.get(0).getRegionName());

    }
    @Test
    public void gson_example(){
        //-----------SERIALIZATION---------------
        //JAVA Collection or POJO to JSON
        Gson gson = new Gson();
        Hazir hazir = new Hazir(15,"Can","Male",87645329845l);

        String abc = gson.toJson(hazir);
        System.out.println(abc);


    }
}
