package workflows;

import extensions.MobileActions;
import extensions.Verifications_Mobile;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.testng.Assert;
import utilities.Base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class MobileFlows extends Base
{

    public static void previous_screen_swipe() {
        MobileActions.swipe(30,
                960 ,
                1050,
                960,300);
        /*
             String main_window = mobileDriver.getWindowHandle();
             int y = mobileDriver.manage().window().getSize().height;
             int x = mobileDriver.manage().window().getSize().width;
             mobileDriver.switchTo().window(main_window);
             MobileActions.swipe(30, y /2, x - 30, y /2,300);
        */
    }


    public static void insurance_car_swipe(boolean left,MobileElement elem) {
        Point p1 = new Point(elem.getLocation().x , elem.getLocation().y   +  (elem.getSize().height /2));
        Point p2 = new Point(elem.getLocation().x  +   elem.getSize().width  , elem.getLocation().y   +  (elem.getSize().height /2));
        if (left == true) // p1 to p2
            MobileActions.swipe(p1.x, p1.y , p2.x, p2.y,500);
        else //p2 to p1
            MobileActions.swipe(p2.x, p2.y , p1.x, p1.y,500);
    }

    public static void login(String id , String phone,boolean gotoNative) {
        Verifications_Mobile.WaitForWebview(10);
        //System.out.println(mobileDriver.getPageSource());
        mobileDriver.findElement(By.xpath("//*[@id='idNumber']")).sendKeys(id);
        mobileDriver.findElement(By.xpath("//*[@id='phoneNumber']")).sendKeys(phone);
        mobileDriver.findElement(By.xpath("//*[@id='continue']")).click();
        if (gotoNative) mobileDriver.context("NATIVE_APP"); //return to app with native
    }

    public static void SDKPopup(MobileElement stop_elem ) {
        do {

            Verifications_Mobile.WaitForElement(androidDriver.findElement(By.xpath("//*[@id='alertTitle']")),10);
            System.out.println("sdk status is : " + mobileDriver.findElement(By.xpath("//*[@id='alertTitle']")).getText());
            System.out.println("sdk status is : " + mobileDriver.findElement(MobileBy.xpath("//*[@id='alertTitle']")).getText());
            //MobileActions.Click(MenoraHW_SDKStatus.btn_ok);
            try {Thread.sleep(500);} catch (InterruptedException e) {e.printStackTrace();} //wait for another popup
        } while (!stop_elem.isDisplayed());

    }

    public static void checkPackageTitle (String driversList) {
        try {
            String[] arr;
            Assert.assertEquals(driversList.substring(0,9),"החבילה של"); //#1
            driversList= driversList.substring(9).trim();
            int spaces = driversList.length() - driversList.replaceAll(" ","").length();
            switch(spaces) {
                case 0: //no space
                    softAssert.assertTrue((driversList.charAt(0)  == ',') || (driversList.charAt(driversList.length() -1) == ','),"redundant comma at " + driversList);
                    //I don't check if "vav" appears before the name because of name that starts with "vav"
                    break;
                case 1: // one spaces
                    arr = driversList.trim().split(" ");
                    softAssert.assertTrue((arr[0].charAt(arr[0].length()-1)  == ',') || (arr[0].charAt(0)  == ','),"redundant comma at " + arr[0]);
                    softAssert.assertTrue((arr[1].charAt(arr[1].length()-1)  == ',') || (arr[1].charAt(0)  == ','),"redundant comma at " + arr[1]);
                    softAssert.assertTrue((arr[1].charAt(0)  != 'ו'),"missing" + " ו "+ "before " + arr[1]);
                    break;
                default: //more than one space
                    arr = driversList.trim().split(" ");
                    for(int i =0 ; i < arr.length -2 ; i++) { //the last item is with "vav" before that there is no comma
                        softAssert.assertTrue((arr[i].charAt(arr[i].length() - 1) != ',' ),"missing comma after " + arr[i]);
                    }
                    softAssert.assertTrue((arr[arr.length -2].charAt(arr[arr.length -2].length()-1)  == ','),"redundant comma at " + arr[arr.length -2]);
                    softAssert.assertTrue((arr[arr.length -1].charAt(arr[arr.length -1].length()-1)  == ','),"redundant comma at " + arr[arr.length -1]);
                    softAssert.assertTrue((arr[arr.length -1].charAt(0)  != 'ו'),"missing" + " ו "+ "before " + arr[arr.length -1]);
            }
        }catch (Exception e) {
            System.out.println("error occored " + e);
            System.out.println("need to recheck package title");
        }
    }
/*
    public static void ReadAccounts() {
        File tFile = new File( System.getProperty("user.dir") + "/DDTFiles/accounts.txt" );
        FileReader fileReader = null;
        users = new ArrayList<ArrayList<String>>();
        try {
            String line = null;
            fileReader = new FileReader( tFile );
            BufferedReader bufferedReader = new BufferedReader( fileReader );
            int lineCounter = 0;
            while ((line = bufferedReader.readLine()) != null ) {
                users.add(new ArrayList<String>()); //add new lines
                users.get(lineCounter).add(0, String.valueOf(lineCounter+1));
                users.get(lineCounter).add(1, line.split(",")[0]); //id
                users.get(lineCounter).add(2, line.split(",")[1]); // phone
                lineCounter++;

            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

*/


}

