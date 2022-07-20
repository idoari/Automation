package sanity;

import io.appium.java_client.MobileBy;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.HashMap;

public class ido {

    //mvn -Dudid=0eff8810493d64b7c47921a4db5db6949212ab26 -Demulator=false -DrunPlatform=ios -Dname=Iphone_8plus_id_92 -Pmobile test


    public static IOSDriver driver;
    public static int expectedAirplaneStatus;
    public static By airplaneBtn = MobileBy.xpath("//XCUIElementTypeSwitch[@name='Airplane Mode']");
    public static By Allow = MobileBy.xpath("//XCUIElementTypeButton[@name='Allow']");
    public static String settingsMenu="com.apple.Preferences";
    public static String stock = "com.apple.stocks";
    public static void main(String[]args) throws Exception {
        HashMap<String, Object> param = new HashMap<>();
        param.put(MobileCapabilityType.PLATFORM_VERSION, "12.1.4");
        param.put(MobileCapabilityType.DEVICE_NAME, "Iphone 8plus id 92");
        param.put(MobileCapabilityType.UDID, "0eff8810493d64b7c47921a4db5db6949212ab26");
        param.put(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        param.put("xcodeOrgId", "2HB98Y672J"); //get it when open dev account on "developer.apple.com/account"
        param.put("xcodeSigningId", "iPhone Developer");
        param.put("platformName", "IOS");
        param.put("useNewWDA", false);
        param.put(MobileCapabilityType.APP, stock);
        DesiredCapabilities capabilities = new DesiredCapabilities(param);
        driver = new IOSDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        try{
            Thread.sleep(5000);
            HashMap<String, Object> value = new HashMap<>();
            value.put("bundleId", settingsMenu);
            driver.executeScript("mobile: launchApp", value);
            driver.executeScript("mobile: activateApp", value);
            boolean status=  enableAirplaneMode();
            System.out.println(status);
            value.put("bundleId", stock);
            driver.executeScript("mobile: launchApp", value);
            driver.executeScript("mobile: activateApp", value);
        }
        finally {
            driver.quit();
        }
    }
    public static boolean enableAirplaneMode(){
        expectedAirplaneStatus=Integer.parseInt(driver.findElement(airplaneBtn).getAttribute("value"));
        if(expectedAirplaneStatus==0){
            driver.findElement(airplaneBtn).click();
            System.out.println("Airplane mode is Turned On:");
            return true;
        }else{
            System.out.println("Airplane mode is already On");
            return false;
        }
    }
    public static boolean disableAirplaneMode(){
        expectedAirplaneStatus=Integer.parseInt(driver.findElement(airplaneBtn).getAttribute("value"));
        if(expectedAirplaneStatus==1){
            driver.findElement(airplaneBtn).click();
            System.out.println("Airplane mode is Turned OFF:");
            return true;
        }else{
            System.out.println("Airplane mode is already OFF");
            return false;
        }
    }
}