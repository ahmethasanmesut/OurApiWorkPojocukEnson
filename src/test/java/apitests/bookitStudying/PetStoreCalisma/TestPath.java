package apitests.bookitStudying.PetStoreCalisma;

import apitests.bookitStudying.PetStoreHazir1.PetStoreHazir;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

public class TestPath {
    @Test
    public void test1(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                pathParam("petId", 9223372036854237231l).when()
                .get("https://petstore.swagger.io/v2/pet/{petId}");

        PetStore petStore = response.body().as(PetStore.class);
        Long id = petStore.getId();
        System.out.println("id = " + id);
        String name = petStore.getName();
        String expectedName = "doggie-boggie";
        System.out.println("name = " + name);
        Assert.assertEquals("Look carefully!!",expectedName,name);
    }
    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON).
                pathParam("petId", 9223372036854237231l).when()
                .get("https://petstore.swagger.io/v2/pet/{petId}");

        PetStore petStore = response.body().as(PetStore.class);
        System.out.println(petStore.getTags().get(0).getId());

    }

}
