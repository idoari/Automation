package extensions;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utilities.CommonOps;

public class ApiActions extends CommonOps {


    @Step("Get Data From Server")
    public static Response get(String url) {
        response = httpRequest.get(url);
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
        return response;
    }

    @Step("Post Data to Server")
    public static void post(JSONObject params,String resource) {
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(params.toJSONString());
        response = httpRequest.post(resource);
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
    }

    @Step("Update Data in Server (put)")
    public static void put(JSONObject params,String resource) {
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(params.toJSONString());
        response = httpRequest.put(resource);
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
    }

    @Step("Delete Data From Server")
    public static void delete(String url,String id) {
        response = httpRequest.delete(url + id);
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
    }

    @Step("extract specific value From JSON")
    public static String extractFromJSON(Response response ,String path) {
        jp = response.jsonPath();
        return jp.get(path).toString();
    }


}
