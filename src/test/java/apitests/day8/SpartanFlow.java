package apitests.day8;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.ConfigurationReader;

import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;

public class SpartanFlow {
   int id;
    @BeforeClass
    public void beforeclass(){
        baseURI= ConfigurationReader.get("spartan_api_url");
    }
    @Test(priority = 1)
    public void POSTNewSpartan(){

    }
    @Test(priority = 2)
    public void PUTExistingSpartan(){

    }
    @Test
    public void PatchExistingSpartan(){

    }
    @Test
    public void GETThatSpartan(){

    }
    @Test
    public void DELETEhatSpartan(){

    }

}
