package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionStateBuilder;
import io.appium.java_client.ios.IOSTouchAction;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static utilities.Base.mobileDriver;
import static utilities.Base.wait;


public class MobileActions  {

    @Step("click on mobile element")
    public static void Click(MobileElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("click on mobile element")
    public static void PermissionAllowDeny(String AllowDeny) {
        String PermissionButton = "//*[@class='android.widget.Button']";
        if (AllowDeny == "Allow")
            PermissionButton = PermissionButton + "[2]";
        else
            PermissionButton = PermissionButton + "[1]";

        wait.until(ExpectedConditions.elementToBeClickable(mobileDriver.findElement(MobileBy.xpath(PermissionButton))));
        mobileDriver.findElement(MobileBy.xpath(PermissionButton)).click();
    }

    @Step("send text to element")
    public static void SendText(MobileElement elem , String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        elem.sendKeys(text);
    }

    @Step("airplane mode ON (Disable Connectivity")
    public static void AirPlaneON(AndroidDriver driver) {
        driver.setConnection(new ConnectionStateBuilder().withWiFiDisabled().withDataDisabled().build());
    }

    @Step("airplane mode OFF (Enable Connectivity")
    public static void AirPlaneOFF(AndroidDriver driver) {
        driver.setConnection(new ConnectionStateBuilder().withWiFiEnabled().withDataEnabled().build());
        //adb shell am start -a android.settings.AIRPLANE_MODE_SETTINGS

    }

    @Step("Tap On Element")
    public static void tap(MobileElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        new TouchAction(mobileDriver).tap(tapOptions().withElement(element(elem))).perform();
    }

    @Step("Tap On Element")
    public static void tapIOS(MobileElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        new IOSTouchAction(mobileDriver).tap(tapOptions().withElement(element(elem))).perform();
    }

    @Step("Tap On Element's position")
    public static void tapOnPosition(MobileElement elem,int x , int y) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        Point p= new Point(x,y);
        new TouchAction(mobileDriver).tap(tapOptions().withElement(element(elem)).withPosition(point(x,y))).perform();
    }

    @Step("longpress On Element")
    public static void longPress(MobileElement elem,int Duration_mSec) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        new TouchAction(mobileDriver).longPress(longPressOptions().withElement(element(elem)).withDuration(ofMillis(Duration_mSec))).release().perform();
    }

    @Step("longpress On Element ios")
    public static void longPressIOS(MobileElement elem,int Duration_mSec) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        new IOSTouchAction(mobileDriver).longPress(longPressOptions().withElement(element(elem)).withDuration(ofMillis(Duration_mSec))).release().perform();
    }

    @Step("screen swipe")
    public static void swipe(int startX , int startY , int endX, int endY , int Duration_mSec) {
        new TouchAction(mobileDriver).press(point(startX, startY)).waitAction(waitOptions(ofMillis(Duration_mSec))).moveTo(point(endX, endY)).release().perform();
        //new TouchAction(mobileDriver).press(point(startX, startY)).moveTo(point(endX, endY)).release().perform();
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);

    }

    @Step("swipe from element to element")
    public static void swipeOnElements(MobileElement startElem,MobileElement endElem , int Duration_mSec) {
        Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
        new TouchAction(mobileDriver).longPress(longPressOptions().withElement(element(startElem)).withDuration(ofMillis(Duration_mSec))).moveTo(element(endElem)).release().perform();
    }

    @Step("scroll until text shows")
    public static void scroll(String text2Find) {
        //mobileDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollIntoView(text(" + '"' + text2Find + '"' + "))");
        //mobileDriver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches((" + '"' + text2Find + '"' + ")).instance(0))");
    }

    @Step("scroll until text shows on IOS")
    public static void scroll_IOS(String text2Find) {
        HashMap<String,Object> scrollObject = new HashMap<>();
        scrollObject.put("direction","down");
        scrollObject.put("name",text2Find);
        mobileDriver.executeScript("mobile:scroll",scrollObject);
    }

    @Step("set slider value on IOS")
    public static void setSlider(String value, MobileElement elem ) {
        if (value.equals("100"))
            elem.setValue("1%"); //slider function get values from 0 to 1 = 1%=100%
        else if (value.equals("0"))
            elem.setValue("0%");
        else
            elem.setValue("0." + value + "%");
    }

    @Step("drag n drop on elements")
    public static void dragNdrop(MobileElement startElem,MobileElement endElem) {
        new TouchAction(mobileDriver).longPress(longPressOptions().withElement(element(startElem))).moveTo(element(endElem)).release().perform();
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Step("zoom On Element")
    public static void zoom(MobileElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        int x = elem.getCenter().x;
        int y = elem.getCenter().y;
        new TouchAction(mobileDriver).press(point(x-10,y)).moveTo(point(x-200,y)).release().perform();

    }

    @Step("pinch Element")
    public static void pinch(MobileElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        int x = elem.getCenter().x;
        int y = elem.getCenter().y;
        new TouchAction(mobileDriver).press(point(x-200,y)).moveTo(point(x-10,y)).release().perform();

    }

    @Step("execute script")
    public static void executeScript(String script) {
        mobileDriver.executeScript(script);
    }

    @Step("taking a screenshot")
    public static void takeScreenshot(String testname) {
        try {
            File scrFile = ((TakesScreenshot)mobileDriver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("imageRepository/" + System.getProperty("name") + "_" + testname +".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}




