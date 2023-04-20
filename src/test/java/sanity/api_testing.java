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

     static JSONObject j ;
     static String token;
     static String userid;
 
    @BeforeClass
    public   void initAPI() {
        RestAssured.baseURI = "https://demoqa.com/";
        httpRequest = RestAssured.given();

        j = new JSONObject();
        j.put("userName", "idoari");
        j.put("password", "Snakebite@10");

    }

    @Test
    public static void getTokenAndUserID() {
        ApiActions.post(j,"Account/v1/Authorized").prettyPrint();
        token = ApiActions.getBodyValues(ApiActions.post(j,"Account/v1/GenerateToken") ,"token");
        System.out.println(token);
        userid = ApiActions.getBodyValues(ApiActions.post(j,"Account/v1/User") ,"code");
        System.out.println(userid);
    }

    @Test
    public static void t2() {
       // ApiActions.put();

    }


}
