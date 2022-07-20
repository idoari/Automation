package extensions;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.CommonOps;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Verifications_Mobile extends CommonOps {

    @Step("verify text in element")
    public static void verifyTextInElement(MobileElement elem, String expected) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getText(),expected);
    }

    @Step("verify text in element")
    public static void existenceOfElement(MobileElement elem) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertTrue(elem.isDisplayed());
    }

    @Step("verify element not displayed")
    public static void nonExistenceOfElement(MobileElement elem) {
        Assert.assertFalse(elem.isDisplayed());
    }


}
