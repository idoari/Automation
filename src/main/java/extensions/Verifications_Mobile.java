package extensions;

import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import utilities.CommonOps;

import java.util.Set;

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

    @Step("waiting for WebView to load")
    public static void WaitForWebview(double upperlimit) {
        double counter = 0.0;
        Set<String> contextNames ;
        while (counter < upperlimit) {
            contextNames = mobileDriver.getContextHandles();
            if (contextNames.size() == 1) {
                try {
                    Thread.sleep(100); //wait 100 milisec
                    counter =  counter +  0.1;
                }
                catch (InterruptedException e) {
                    System.out.println("error occurred: " + e);;
                }
            }
            else  {
                mobileDriver.context(contextNames.toArray()[contextNames.size() -1].toString());
                break;
            }
        }
    }

    @Step("waiting for WebView to load")
    public static void WaitForElement(WebElement elem, double upperlimit) {
        double counter = 0.0;
        while (counter < upperlimit) {

            if (elem.getLocation().x == 0) {
                try {
                    Thread.sleep(100); //wait 100 milisec
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter = counter + 0.1;
                System.out.println(counter);
            }
            else{
                break;
            }
        }
    }


    @Step("verify element not displayed")
    public static void nonExistenceOfElement(MobileElement elem) {
        Assert.assertFalse(elem.isDisplayed());
    }


}
