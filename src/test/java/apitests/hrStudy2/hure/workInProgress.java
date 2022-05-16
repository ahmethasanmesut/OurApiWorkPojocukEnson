package apitests.hrStudy2.hure;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
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
        Response response = given().accept(ContentType.JSON)
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
    public void testPrettyPrint(){
       //check all manager_id at item list
        //find our manager_id == null.
        Response response = given().accept(ContentType.XML)
                .when().get("/employees");
        String actMap = response.body().prettyPrint();

        // System.out.println("generalMap = " + generalMap);
       // List<Map<String,Object>> items = (List<Map<String, Object>>) generalMap.get("items");
        //System.out.println("items = " + items);

        String expMap = "{\n" +
                "    \"items\": [\n" +
                "        {\n" +
                "            \"employee_id\": 100,\n" +
                "            \"first_name\": \"Steven\",\n" +
                "            \"last_name\": \"King\",\n" +
                "            \"email\": \"SKING\",\n" +
                "            \"phone_number\": \"515.123.4567\",\n" +
                "            \"hire_date\": \"2003-06-17T00:00:00Z\",\n" +
                "            \"job_id\": \"AD_PRES\",\n" +
                "            \"salary\": 2000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": null,\n" +
                "            \"department_id\": 90,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/100\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 101,\n" +
                "            \"first_name\": \"Neena\",\n" +
                "            \"last_name\": \"Kochhar\",\n" +
                "            \"email\": \"NKOCHHAR\",\n" +
                "            \"phone_number\": \"515.123.4568\",\n" +
                "            \"hire_date\": \"2005-09-21T00:00:00Z\",\n" +
                "            \"job_id\": \"AD_VP\",\n" +
                "            \"salary\": 17000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 90,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/101\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 102,\n" +
                "            \"first_name\": \"Lex\",\n" +
                "            \"last_name\": \"De Haan\",\n" +
                "            \"email\": \"LDEHAAN\",\n" +
                "            \"phone_number\": \"515.123.4569\",\n" +
                "            \"hire_date\": \"2001-01-13T00:00:00Z\",\n" +
                "            \"job_id\": \"AD_VP\",\n" +
                "            \"salary\": 17000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 90,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/102\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 103,\n" +
                "            \"first_name\": \"Alexander\",\n" +
                "            \"last_name\": \"Hunold\",\n" +
                "            \"email\": \"AHUNOLD\",\n" +
                "            \"phone_number\": \"590.423.4567\",\n" +
                "            \"hire_date\": \"2006-01-03T00:00:00Z\",\n" +
                "            \"job_id\": \"IT_PROG\",\n" +
                "            \"salary\": 9000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 102,\n" +
                "            \"department_id\": 60,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/103\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 104,\n" +
                "            \"first_name\": \"Bruce\",\n" +
                "            \"last_name\": \"Ernst\",\n" +
                "            \"email\": \"BERNST\",\n" +
                "            \"phone_number\": \"590.423.4568\",\n" +
                "            \"hire_date\": \"2007-05-21T00:00:00Z\",\n" +
                "            \"job_id\": \"IT_PROG\",\n" +
                "            \"salary\": 6000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 103,\n" +
                "            \"department_id\": 60,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/104\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 105,\n" +
                "            \"first_name\": \"David\",\n" +
                "            \"last_name\": \"Austin\",\n" +
                "            \"email\": \"DAUSTIN\",\n" +
                "            \"phone_number\": \"590.423.4569\",\n" +
                "            \"hire_date\": \"2005-06-25T00:00:00Z\",\n" +
                "            \"job_id\": \"IT_PROG\",\n" +
                "            \"salary\": 4800,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 103,\n" +
                "            \"department_id\": 60,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/105\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 106,\n" +
                "            \"first_name\": \"Valli\",\n" +
                "            \"last_name\": \"Pataballa\",\n" +
                "            \"email\": \"VPATABAL\",\n" +
                "            \"phone_number\": \"590.423.4560\",\n" +
                "            \"hire_date\": \"2006-02-05T00:00:00Z\",\n" +
                "            \"job_id\": \"IT_PROG\",\n" +
                "            \"salary\": 4800,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 103,\n" +
                "            \"department_id\": 60,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/106\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 107,\n" +
                "            \"first_name\": \"Diana\",\n" +
                "            \"last_name\": \"Lorentz\",\n" +
                "            \"email\": \"DLORENTZ\",\n" +
                "            \"phone_number\": \"590.423.5567\",\n" +
                "            \"hire_date\": \"2007-02-07T00:00:00Z\",\n" +
                "            \"job_id\": \"IT_PROG\",\n" +
                "            \"salary\": 4200,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 103,\n" +
                "            \"department_id\": 60,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/107\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 108,\n" +
                "            \"first_name\": \"Nancy\",\n" +
                "            \"last_name\": \"Greenberg\",\n" +
                "            \"email\": \"NGREENBE\",\n" +
                "            \"phone_number\": \"515.124.4569\",\n" +
                "            \"hire_date\": \"2002-08-17T00:00:00Z\",\n" +
                "            \"job_id\": \"FI_MGR\",\n" +
                "            \"salary\": 12008,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 101,\n" +
                "            \"department_id\": 100,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/108\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 109,\n" +
                "            \"first_name\": \"Daniel\",\n" +
                "            \"last_name\": \"Faviet\",\n" +
                "            \"email\": \"DFAVIET\",\n" +
                "            \"phone_number\": \"515.124.4169\",\n" +
                "            \"hire_date\": \"2002-08-16T00:00:00Z\",\n" +
                "            \"job_id\": \"FI_ACCOUNT\",\n" +
                "            \"salary\": 9000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 108,\n" +
                "            \"department_id\": 100,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/109\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 110,\n" +
                "            \"first_name\": \"John\",\n" +
                "            \"last_name\": \"Chen\",\n" +
                "            \"email\": \"JCHEN\",\n" +
                "            \"phone_number\": \"515.124.4269\",\n" +
                "            \"hire_date\": \"2005-09-28T00:00:00Z\",\n" +
                "            \"job_id\": \"FI_ACCOUNT\",\n" +
                "            \"salary\": 8200,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 108,\n" +
                "            \"department_id\": 100,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/110\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 111,\n" +
                "            \"first_name\": \"Ismael\",\n" +
                "            \"last_name\": \"Sciarra\",\n" +
                "            \"email\": \"ISCIARRA\",\n" +
                "            \"phone_number\": \"515.124.4369\",\n" +
                "            \"hire_date\": \"2005-09-30T00:00:00Z\",\n" +
                "            \"job_id\": \"FI_ACCOUNT\",\n" +
                "            \"salary\": 7700,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 108,\n" +
                "            \"department_id\": 100,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/111\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 112,\n" +
                "            \"first_name\": \"Jose Manuel\",\n" +
                "            \"last_name\": \"Urman\",\n" +
                "            \"email\": \"JMURMAN\",\n" +
                "            \"phone_number\": \"515.124.4469\",\n" +
                "            \"hire_date\": \"2006-03-07T00:00:00Z\",\n" +
                "            \"job_id\": \"FI_ACCOUNT\",\n" +
                "            \"salary\": 7800,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 108,\n" +
                "            \"department_id\": 100,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/112\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 113,\n" +
                "            \"first_name\": \"Luis\",\n" +
                "            \"last_name\": \"Popp\",\n" +
                "            \"email\": \"LPOPP\",\n" +
                "            \"phone_number\": \"515.124.4567\",\n" +
                "            \"hire_date\": \"2007-12-07T00:00:00Z\",\n" +
                "            \"job_id\": \"FI_ACCOUNT\",\n" +
                "            \"salary\": 6900,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 108,\n" +
                "            \"department_id\": 100,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/113\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 114,\n" +
                "            \"first_name\": \"Den\",\n" +
                "            \"last_name\": \"Raphaely\",\n" +
                "            \"email\": \"DRAPHEAL\",\n" +
                "            \"phone_number\": \"515.127.4561\",\n" +
                "            \"hire_date\": \"2002-12-07T00:00:00Z\",\n" +
                "            \"job_id\": \"PU_MAN\",\n" +
                "            \"salary\": 11000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 30,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/114\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 115,\n" +
                "            \"first_name\": \"Alexander\",\n" +
                "            \"last_name\": \"Khoo\",\n" +
                "            \"email\": \"AKHOO\",\n" +
                "            \"phone_number\": \"515.127.4562\",\n" +
                "            \"hire_date\": \"2003-05-18T00:00:00Z\",\n" +
                "            \"job_id\": \"PU_CLERK\",\n" +
                "            \"salary\": 3100,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 114,\n" +
                "            \"department_id\": 30,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/115\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 116,\n" +
                "            \"first_name\": \"Shelli\",\n" +
                "            \"last_name\": \"Baida\",\n" +
                "            \"email\": \"SBAIDA\",\n" +
                "            \"phone_number\": \"515.127.4563\",\n" +
                "            \"hire_date\": \"2005-12-24T00:00:00Z\",\n" +
                "            \"job_id\": \"PU_CLERK\",\n" +
                "            \"salary\": 2900,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 114,\n" +
                "            \"department_id\": 30,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/116\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 117,\n" +
                "            \"first_name\": \"Sigal\",\n" +
                "            \"last_name\": \"Tobias\",\n" +
                "            \"email\": \"STOBIAS\",\n" +
                "            \"phone_number\": \"515.127.4564\",\n" +
                "            \"hire_date\": \"2005-07-24T00:00:00Z\",\n" +
                "            \"job_id\": \"PU_CLERK\",\n" +
                "            \"salary\": 2800,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 114,\n" +
                "            \"department_id\": 30,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/117\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 118,\n" +
                "            \"first_name\": \"Guy\",\n" +
                "            \"last_name\": \"Himuro\",\n" +
                "            \"email\": \"GHIMURO\",\n" +
                "            \"phone_number\": \"515.127.4565\",\n" +
                "            \"hire_date\": \"2006-11-15T00:00:00Z\",\n" +
                "            \"job_id\": \"PU_CLERK\",\n" +
                "            \"salary\": 2600,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 114,\n" +
                "            \"department_id\": 30,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/118\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 119,\n" +
                "            \"first_name\": \"Karen\",\n" +
                "            \"last_name\": \"Colmenares\",\n" +
                "            \"email\": \"KCOLMENA\",\n" +
                "            \"phone_number\": \"515.127.4566\",\n" +
                "            \"hire_date\": \"2007-08-10T00:00:00Z\",\n" +
                "            \"job_id\": \"PU_CLERK\",\n" +
                "            \"salary\": 2500,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 114,\n" +
                "            \"department_id\": 30,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/119\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 120,\n" +
                "            \"first_name\": \"Matthew\",\n" +
                "            \"last_name\": \"Weiss\",\n" +
                "            \"email\": \"MWEISS\",\n" +
                "            \"phone_number\": \"650.123.1234\",\n" +
                "            \"hire_date\": \"2004-07-18T00:00:00Z\",\n" +
                "            \"job_id\": \"ST_MAN\",\n" +
                "            \"salary\": 8000,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 50,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/120\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 121,\n" +
                "            \"first_name\": \"Adam\",\n" +
                "            \"last_name\": \"Fripp\",\n" +
                "            \"email\": \"AFRIPP\",\n" +
                "            \"phone_number\": \"650.123.2234\",\n" +
                "            \"hire_date\": \"2005-04-10T00:00:00Z\",\n" +
                "            \"job_id\": \"ST_MAN\",\n" +
                "            \"salary\": 8200,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 50,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/121\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 122,\n" +
                "            \"first_name\": \"Payam\",\n" +
                "            \"last_name\": \"Kaufling\",\n" +
                "            \"email\": \"PKAUFLIN\",\n" +
                "            \"phone_number\": \"650.123.3234\",\n" +
                "            \"hire_date\": \"2003-05-01T00:00:00Z\",\n" +
                "            \"job_id\": \"ST_MAN\",\n" +
                "            \"salary\": 7900,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 50,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/122\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 123,\n" +
                "            \"first_name\": \"Shanta\",\n" +
                "            \"last_name\": \"Vollman\",\n" +
                "            \"email\": \"SVOLLMAN\",\n" +
                "            \"phone_number\": \"650.123.4234\",\n" +
                "            \"hire_date\": \"2005-10-10T00:00:00Z\",\n" +
                "            \"job_id\": \"ST_MAN\",\n" +
                "            \"salary\": 6500,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 50,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/123\"\n" +
                "                }\n" +
                "            ]\n" +
                "        },\n" +
                "        {\n" +
                "            \"employee_id\": 124,\n" +
                "            \"first_name\": \"Kevin\",\n" +
                "            \"last_name\": \"Mourgos\",\n" +
                "            \"email\": \"KMOURGOS\",\n" +
                "            \"phone_number\": \"650.123.5234\",\n" +
                "            \"hire_date\": \"2007-11-16T00:00:00Z\",\n" +
                "            \"job_id\": \"ST_MAN\",\n" +
                "            \"salary\": 5800,\n" +
                "            \"commission_pct\": null,\n" +
                "            \"manager_id\": 100,\n" +
                "            \"department_id\": 50,\n" +
                "            \"links\": [\n" +
                "                {\n" +
                "                    \"rel\": \"self\",\n" +
                "                    \"href\": \"http://3.84.52.125:1000/ords/hr/employees/124\"\n" +
                "                }\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"hasMore\": true,\n" +
                "    \"limit\": 25,\n" +
                "    \"offset\": 0,\n" +
                "    \"count\": 25,\n" +
                "    \"links\": [\n" +
                "        {\n" +
                "            \"rel\": \"self\",\n" +
                "            \"href\": \"http://3.84.52.125:1000/ords/hr/employees/\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rel\": \"edit\",\n" +
                "            \"href\": \"http://3.84.52.125:1000/ords/hr/employees/\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rel\": \"describedby\",\n" +
                "            \"href\": \"http://3.84.52.125:1000/ords/hr/metadata-catalog/employees/\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rel\": \"first\",\n" +
                "            \"href\": \"http://3.84.52.125:1000/ords/hr/employees/\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"rel\": \"next\",\n" +
                "            \"href\": \"http://3.84.52.125:1000/ords/hr/employees/?offset=25\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
           Assert.assertEquals(expMap,actMap);
    }
    @Test
    public void ListMap(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees");
        Map generalMap = response.body().as(Map.class);
        List<Map<String,Object>> items = (List<Map<String, Object>>) generalMap.get("items");
        String actCommission_pct = (String) items.get(0).get("commission_pct");
        System.out.println("commission_pct = " + actCommission_pct);
        String expCommission_pct = null;
        Assert.assertEquals(expCommission_pct,actCommission_pct);
        List<Map<String,Object>> links = (List<Map<String, Object>>) generalMap.get("links");
        Object rel = links.get(0).get("rel");
        System.out.println("rel = " + rel);

        System.out.println("generalMap.get(\"hasMore\") = " + generalMap.get("hasMore"));


    }
    @Test
    public void testLinks(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees");

        Map generalTable = response.body().as(Map.class);

        List<Map<String,Object>> links = (List<Map<String,Object>>) generalTable.get("links");

        Object relotivo = links.get(2).get("rel");
        System.out.println("relotivo = " + relotivo);

    }
    @Test
    public void testimprove(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees");
         Map genTable = response.body().as(Map.class);
        List<Map<String,Object>> links =  (List<Map<String,Object>>) genTable.get("links");
        Object href = links.get(4).get("href");
        System.out.println("href = " + href);



        List<Map<String,Object>> items = (List<Map<String, Object>>) genTable.get("items");
        int counter=0;
        for (int i = 0; i < items.size(); i++) {
            String actCommission_pct = (String) items.get(i).get("commission_pct");
            if (actCommission_pct==null){
                counter++;
               System.out.println(items.get(i).get("commission_pct"));
            }


        }
        System.out.println(counter);
    }
    @Test
    public void pathTest(){
        Response response = given().accept(ContentType.JSON)
                .when().get("/employees");
        List <Integer> salaries = response.body().path("items.salary");
        System.out.println(salaries.get(4));
        System.out.println("salaries = " + salaries);

        Object hasMore = response.body().path("hasMore");
        System.out.println(hasMore);

        Object limit = response.body().path("limit");
        System.out.println(limit);

        JsonPath jsonPath = response.body().jsonPath();
        Object limit1 = jsonPath.get("limit");
        System.out.println("limit1 = " + limit1);

        List<Object> list = jsonPath.getList("items.salary");
        System.out.println("list = " + list);


    }
    @Test
    public void hamchrest(){
        given().accept(ContentType.JSON)
                .and().pathParam("id",100)
                .when().get("/employees/{id}")
                .then().log().all().statusCode(200)
                .and().contentType("application/json")
                .and().body("email",Matchers.is("SKING"),
                "salary",Matchers.is(2000),
                        "last_name",Matchers.equalTo("King"))
                .headers("Transfer-Encoding",Matchers.equalTo("chunked"));
    }

    }


