package sanity;


import extensions.MobileActions;
import extensions.Verifications_Mobile;
import io.appium.java_client.MobileBy;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utilities.CommonOps;
import utilities.ManagePages;
import java.io.IOException;
import java.util.Set;

@Listeners(utilities.Listeners.class)
public class MenoraMobile_Onboarding extends CommonOps
{

    @BeforeClass
    public void startSession () throws IOException {
        int port = findAvailableAppiumPort();
        if (port == -1) {
            System.out.println("can't find available port. terminating run....");
            System.exit(0); //exit the run if no port is available
        }
        service = startAppium(port); //start the server
        if (System.getProperty("runPlatform").trim().equalsIgnoreCase("ios")) { //get from jenkins
                 iosDriver = initMobileIOS(port);
                if (iosDriver == null) {
                    System.out.println("Fail To Initialize Appium.");
                    closeAppium();
                    System.exit(0);
                }
                iosDriver.resetApp();
                mobileDriver = iosDriver;

        }
        else { //android
            androidDriver = initMobileAndroid(port);
            if (androidDriver == null) {
                System.out.println("Fail To Initialize Appium.");
                closeAppium();
                System.exit(0);
            }
            mobileDriver = androidDriver;
        }
        ManagePages.initMenoraHW();
    }


   @Test(description = "test01 - check visibility")
    @Description("this test check visibility of onboarding screen")
    public void test_H1() throws InterruptedException {Thread.sleep(2000); //wait for splash screen to disappear
       Verifications_Mobile.verifyTextInElement(MenoraHW_onboarding.lb_goToApp,"קדימה לאפליקציה");

    }

    @Test(description = "test02 - add a new candidate")
    @Description("this test adds a new candidate to CSV file")
    public void test_H4()  {
        MobileActions.takeScreenshot("H4");
        MobileFlows.previous_screen_swipe();
        Verifications_Mobile.existenceOfElement(MenoraHW_onboarding.btn_goToApp);
    }


    @Test(description = "test01 - add a new candidate")
    @Description("this test adds a new candidate to CSV file")
    public void test_H5()  {
        MobileActions.Click(MenoraHW_onboarding.btn_goToApp);
        Verifications_Mobile.existenceOfElement(MenoraHW_connection.lb_home);
        MobileActions.takeScreenshot("H5");
    }

    @Test(description = "test01 - add a new candidate")
    @Description("this test adds a new candidate to CSV file")
    public void test_I6() throws InterruptedException {
        MobileActions.Click(MenoraHW_connection.btn_b2c);
        Thread.sleep(5000);
        Set<String> contextNames = androidDriver.getContextHandles();
        Thread.sleep(3000);
        for (String contextName : contextNames) {
            System.out.println(contextName); //prints out something like NATIVE_APP \n WEBVIEW_1
        }
        mobileDriver.context(contextNames.toArray()[1].toString()); // set context to WEBVIEW_1
        androidDriver.context(contextNames.toArray()[1].toString());
        //get chrome version and download chrome driver

        try {
            androidDriver.findElement(By.xpath("//*[@id='idNumber']")).sendKeys("320633746");
        }catch (Exception E1) {
            System.out.println("e1");
            try {
                androidDriver.findElementById("idNumber").sendKeys("320633746");
            }catch (Exception E2) {
                System.out.println("e2");
                try {
                    androidDriver.findElement(MobileBy.id("idNumber")).sendKeys("320633746");
                }catch (Exception E3) {
                    System.out.println("e3");
                    try {
                        androidDriver.findElement(MobileBy.AccessibilityId("idNumber")).sendKeys("320633746");
                    }catch (Exception E4) {
                        System.out.println("e4");
                    }
                }

            }

        }

       // MobileActions.SendText(MenoraHW_b2c.txt_id,"320633746");
        //MobileActions.SendText(MenoraHW_b2c.txt_phone,"0545282043");
       // MobileActions.Click(MenoraHW_b2c.btn_continue);
        Thread.sleep(5000);
        ///androidDriver.context("NATIVE_APP"); //return to app with native
        //Verifications_Mobile.existenceOfElement(MenoraHW_permistionAsk.lb_title);
        System.exit(0);
    }

    @Test(description = "test01 - add a new candidate")
    @Description("this test adds a new candidate to CSV file")
    public void test_i7() throws InterruptedException {
        MobileActions.Click(MenoraHW_permistionAsk.btn_continue);
        MobileActions.PermissionAllowDeny("Allow"); //LOCATION ALLOW - ALLOW
        Thread.sleep(1000);
        MobileActions.PermissionAllowDeny("Allow"); //BATTERY OPTIMIZER - ALLOW
        Thread.sleep(2500);
        // By loc_acceptAlert = MobileBy.ByAndroidUIAutomator.AndroidUIAutomator("new UiSelector().text(\"YES\")");
        //By loc_dismissAlert = MobileBy.ByAndroidUIAutomator.AndroidUIAutomator("new UiSelector().text(\"NO\")");
        //////////////
        //WebElement positiveButtonElementById=androidDriver.findElement(By.id(“com.fittingnzidd.cherripik.development:id/positiveButton”));


        //Set<String> win = mobileDriver.getWindowHandles();
        //System.out.println("abra");
        //for (String ido : win) {
         //   System.out.println(ido);
       // }
        try {
            androidDriver.findElement(By.xpath("//*[@text='OK']")).click();

            System.out.println(androidDriver.findElementByAccessibilityId("alertTitle").getText());
        }catch (Exception E1) {
            System.out.println("e1");
            try {
                System.out.println(androidDriver.findElementById("alertTitle").getText());
            }catch (Exception E2) {
                System.out.println("e2");
                try {
                    System.out.println(androidDriver.findElement(MobileBy.id("alertTitle")).getText());
                }catch (Exception E3) {
                    System.out.println("e3");
                    try {
                        System.out.println(androidDriver.findElement(MobileBy.AccessibilityId("alertTitle")).getText());
                    }catch (Exception E4) {
                        System.out.println("e4");
                    }
                }

            }

        }



        //System.out.println(androidDriver.findElement(By.id("alertTitle")).getText());
        //Thread.sleep(2000);
        //while (MenoraHW_SDKStatus.btn_ok.isDisplayed()) {
         //   MobileActions.Click(MenoraHW_SDKStatus.btn_ok);
           // Thread.sleep(1000);
        //}
        //androidDriver.activateApp("com.menora_myway.test");
        //Verifications_Mobile.verifyTextInElement(MenoraHW_gotIt.lb_title,"יש לנו את זה!\nעוברים לאפליקציה");
        //long starttime = System.nanoTime();
        //wait.until(ExpectedConditions.visibilityOf(MenoraHW_onboarding.lb_goToApp));
        //long endtime = System.nanoTime();
    }

*/

  //  @AfterClass
   // public void end_session() {
      //  closeAppium();
   // }


}
