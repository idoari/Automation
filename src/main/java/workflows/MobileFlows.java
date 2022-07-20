package workflows;

import extensions.MobileActions;
import io.appium.java_client.AppiumDriver;
import utilities.Base;


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
}

