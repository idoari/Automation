package sanity;

import extensions.ApiActions;
import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Base;

public class api_testing extends Base {

    @BeforeClass
    public   void initAPI() {
        RestAssured.baseURI = "https://demoqa.com/";
        httpRequest = RestAssured.given();

        JSONObject j = new JSONObject();
        j.put("userName", "idoari");
        j.put("password", "Snakebite@10");

        System.out.println(ApiActions.getBodyValues(ApiActions.post(j,"Account/v1/GenerateToken") ,"token"));
    }

    @Test
    public static void t1() {
        System.out.println(ApiActions.get("BookStore/v1/Books").prettyPrint() + "\n" + "*********");
        //ApiActions.put("/posts/1","/posts/1","titlw","This is an example of changing text with PUT");
        //System.out.println(ApiActions.get("/posts/1").getBody().asString() + "\n" + "*********");


    }
}
