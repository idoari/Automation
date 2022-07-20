package extensions;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.sikuli.script.FindFailed;
import org.testng.Assert;
import utilities.CommonOps;
import java.util.List;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class Verifications_Web extends CommonOps {

    @Step("verify text in element")
    public static void verifyTextInElement(WebElement elem,String expected) {
        wait.until(ExpectedConditions.visibilityOf(elem));
        assertEquals(elem.getText(),expected);
    }

    @Step("verify text in elements list")
    public static void verifyTextInElements(List<WebElement> elem,String expected) {
        Boolean found = false;
        for (int i =0 ; i < elem.size() ; i++) {
            if (elem.get(i).getText().equals(expected)) {
                found=true ;
                break;
            }
        }
        Assert.assertTrue(found);
    }

    @Step("verify elements list count")
    public static void verifyNumberOfElements(List<WebElement> elems,int expected) {
        wait.until(ExpectedConditions.visibilityOf(elems.get(elems.size() -1)));
        assertEquals(elems.size(),expected);
    }

    @Step("verify element displayed")
    public static void existenceOfElement(List<WebElement> elems) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elems));
        Assert.assertTrue(elems.size() > 0);
    }

    @Step("verify element not displayed")
    public static void nonExistenceOfElement(List<WebElement> elems) {
        Assert.assertTrue(elems.size() == 0);
    }

    @Step("verify visibility Of Elements List (soft-Assertion)")
    public static void visibilityOfElements(List<WebElement> elems) {
        for (WebElement elem : elems) {
            softAssert.assertTrue(elem.isDisplayed(),"sorry " + elem.getText() + " not displayed.");
        }
        softAssert.assertAll("some element are not displayed.");
    }

    /*
    @Step("verify image Element presence (Sikuli)")
    public static void visualElement(String ExpectedImageName) {
        try {
            //screen.find(getData("imageRepository") +ExpectedImageName + ".PNG");
        } catch (FindFailed findFailed) {
            System.out.println("error finding image: " + findFailed);
            fail("error finding image: " + findFailed);
        }


    }
*/

    @Step("check duplicate Elements List")
    public static void checkDuplicateElements(List<WebElement> elems) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elems));
        for (int i = 0; i < elems.size(); i++) {
            for (int g = 0; g < elems.size(); g++) {
                if (elems.get(i).equals(elems.get(g).getText())) {
                    System.out.println("duplicate found : " + elems.get(i).getText());
                    break;
                }
            }
        }
    }

    @Step("verify element not displayed")
    public static void selectSpecificCheckbox(List<WebElement> chk, List<WebElement> table, String expected,int row_count) {
        wait.until(ExpectedConditions.visibilityOfAllElements(chk));
        wait.until(ExpectedConditions.visibilityOfAllElements(table));
        for (int i =0 ; i < table.size() ; i++) {
            if (row_count == 1) {
                if (table.get(i).getText().equals(expected)) {
                    chk.get(i).click();
                    break;
                }
            }
            else {
                if ((table.get(i).findElement(By.xpath("//td[3]")).getText().trim() + " " +table.get(i).findElement(By.xpath("//td[4]")).getText().trim()).equals(expected)) {
                    chk.get(i).click();
                    break;
                }
            }
        }
    }



    //(for REST api testing)
    @Step("Compare expected text to actual text")
    public static void VerifyText(String actual , String expected) {
        Assert.assertEquals(actual,expected);
    }

    @Step("Compare expected number to actual number")
    public static void VerifyNumber(int actual , int expected) {

        Assert.assertEquals(actual,expected);
    }

}
