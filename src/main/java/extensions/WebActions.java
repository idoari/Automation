package extensions;

import com.google.common.util.concurrent.Uninterruptibles;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import utilities.CommonOps;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class WebActions extends CommonOps {

    protected static Actions action;

    @Step("click on element")
    public static void Click(WebElement elem) {
        wait.until(ExpectedConditions.elementToBeClickable(elem));
        elem.click();
    }

    @Step("send text to element")
    public static void UpdateText(WebElement elem , String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        elem.sendKeys(text);
    }

    @Step("send keyboard key press")
    public static void SendKeyPress(WebElement elem , Keys value) {
        elem.sendKeys(value);
    }

    @Step("select from dropdown")
    public static void UpdateDropDown(WebElement elem , String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        Select dropdown = new Select(elem);
        dropdown.selectByVisibleText(text);
    }

    @Step("mouse hover over element")
    public static void MouseHover(WebElement elem1,WebElement elem2) {
        action.moveToElement(elem1).moveToElement(elem2).click().build().perform();
    }

    @Step("mouse hover over element")
    public static void MouseHover(WebElement elem1) {
        action.moveToElement(elem1).click().build().perform();
    }

    @Step("send text to element as human")
    public static void UpdateTextHuman(WebElement elem , String text) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        for (char ch : text.toCharArray()) {
            Uninterruptibles.sleepUninterruptibly(500, TimeUnit.MILLISECONDS);
            elem.sendKeys(ch + "");
        }
    }

    @Step("scrolling the web page")
    public static void scrolldown() {
        JavascriptExecutor js = (JavascriptExecutor) mobileDriver;
        js.executeScript("window.scrollBy(0,1000)","");
    }

    @Step("download file")
    public static void downloadFileFromAPPCenter(String fileURL) throws IOException {
       //https://install.appcenter.ms/
        //WebActions.Click("//*[@class='_23wN5TUrx _2Xp9P6six']");
        FileUtils.copyURLToFile(new URL(fileURL), new File("AUT/" + fileURL.split("/")[fileURL.split("/").length -1]));
    }


}
