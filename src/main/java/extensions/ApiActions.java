package extensions;

import com.google.gson.internal.bind.util.ISO8601Utils;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import utilities.CommonOps;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApiActions extends CommonOps {


    @Step("Get Data From Server")
    public static Response get(String url) {
        response = httpRequest.get(url); // the url include the parameters to pass like key etc... (http://www.google.com/index?para1=value1&para2=value2)
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
        return response;
    }

    @Step("Post Data to Server")
    public static Response post(JSONObject params,String url) {
        httpRequest.header("Content-Type","application/json");
        //System.out.println(params.toJSONString());
        httpRequest.body(params.toJSONString());
        response = httpRequest.post(url);
        //Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
        return response;
    }

    @Step("Update Data in Server (put)")
    public static void put(String getUrl,String putURL,String key , String newValue) {
        response = httpRequest.get(getUrl);
        String data = response.jsonPath().getJsonObject("$").toString();
        data = data.replace("[","").replace("]","").replace("{" , "").replace("}","");
        String arr[] = data.split(",");
        String arr2[];
        Map o = new LinkedHashMap();
        for (int i =0 ; i <arr.length ; i++) {
            arr2 = arr[i].split("=");
            if (arr2[0].trim().equals(key))
                o.put("\"" +arr2[0].trim() +"\"",newValue.trim() +"\"");
            else
                o.put("\"" +arr2[0].trim() +"\"" ,"\"" +arr2[1].trim() +"\"");
        }

        JSONObject jo = new JSONObject(o);
        httpRequest.header("Content-Type","application/json");
        System.out.println(jo.toString());
        httpRequest.body(jo.toString());
        response = httpRequest.put(putURL);
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());


    }

    @Step("Delete Data From Server")
    public static void delete(String url,String id) {
        response = httpRequest.delete(url + id);
        Assert.assertEquals(response.statusCode(),200,"Unexpected Error Code " + response.statusCode());
    }

    @Step("extract specific value From JSON")
    public static String getBodyValues(Response response ,String path) {
        jp=response.jsonPath();
        return jp.get(path).toString();
    }

    public static String getHeaderValue(Response response,String headerKey) {
        return response.header(headerKey);
    }

    public static void PrintHeaders(Response response) {
        //headers is a MAP list (key and value of each item in the list)
        for (Header h : response.headers()) {
            System.out.println(h.getName() + " = " + h.getValue());
        }
    }

    public static void PrintBody(Response response) {
        System.out.println(response.getBody().asString());
    }

    public static boolean verifyContainText(Response response,boolean inHeader,String search) {
        if (inHeader) {
              return  response.headers().toString().contains(search);
        }else {
            return response.getBody().asString().contains(search);
        }
    }

}
