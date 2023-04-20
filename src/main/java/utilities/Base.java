package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.util.Properties;

public class Base {

    //general
    public static WebDriverWait wait;
    protected static SoftAssert softAssert ;
    //protected static Screen screen;  //for sixuli graphical identifier
    protected static String platform ;
    protected static Properties prop; //for properites config file

    //web
    protected static WebDriver driver; //used by electron,winappdriver as well (electron is a web base app)
    protected static Actions action ;

    //mobile
    public static AppiumDriver mobileDriver;
    public static IOSDriver iosDriver;
    public static AndroidDriver androidDriver;
    public static AppiumDriverLocalService service; //close and open the appium session

    //page objects - mobile
    public static pageObjects.Youtube.homepage Youtube_home;






    //Rest API
    protected static RequestSpecification httpRequest;
    protected static Response response;
    protected static JSONObject params = new JSONObject();

    protected static JsonPath jp;


    //Database
    //protected static Connection con;
    // protected static Statement stat;
    //protected static ResultSet rs;




}
