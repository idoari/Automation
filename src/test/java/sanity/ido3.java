package sanity;

public class ido3 {
/*
    static boolean cleanIOSApplication(RemoteWebDriver driver){
        //define visual driver and native driver:
        Map<String, Object> params = new HashMap<>();
        //clean iOS
        try {
            //open Battery Saver application
            params.put("name", "Battery Saver");
            driver.executeScript("mobile:application:open", params);
            //click on Disk in lower application menu
            switchToContext(driver, "NATIVE_APP");
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.findElementByXPath("//*[@label=\"Disk\"]").click();
            //click on clean cache
            driver.findElementByXPath("//*[@label=\"btn jj off\"]").click();
            //handle pop up in case it s there
            try {
                driver.findElementByXPath("//*[@label=\"OK\"]").click();
            } catch (Exception e) {
                //no pop up, move on with script
            }
            //validate cache cleaned
            Map<String, Object> params2 = new HashMap<>();
            params2.put("content", "\"Junk Cleaned\"");
            params2.put("timeout", "120");
            driver.executeScript("mobile:checkpoint:text", params2);
            //close battery doctor:
            driver.executeScript("mobile:application:close", params);
            return true;
        } catch (Exception e) {
            System.out.println("Failed to clean application cache");
            return false;
        }
    }
    RemoteWebDriver C# Code Example
    static bool cleanIOSApplication(RemoteWebDriverExtended driver)
    {

        //define visual driver and native driver:
        Dictionary<String, Object> params1 = new Dictionary<String, Object>();

        //clean iOS
        try
        {
            //open Battery Saver application
            params1.Add("name", "Battery Saver");
            driver.ExecuteScript("mobile:application:open", params1);

            //click on Disk in lower application menu
            driver.Context = "NATIVE_APP";
            driver.Manage().Timeouts().ImplicitlyWait(new TimeSpan(0, 0, 15));
            driver.FindElementByXPath("//*[@label=\"Disk\"]").Click();

            //click on clean cache
            driver.FindElementByXPath("//*[@label=\"btn jj off\"]").Click();

            //handle pop up in case it s there
            try
            {
                driver.FindElementByXPath("//*[@label=\"OK\"]").Click();

            }
            catch (Exception e)
            {
                //no pop up, move on with script
            }

            //validate cache cleaned
            Dictionary<String, Object> params2 = new Dictionary<String, Object>();
            params2.Add("content", "\"Junk Cleaned\"");
            params2.Add("timeout", "120");
            driver.ExecuteScript("mobile:checkpoint:text", params2);


            //close battery doctor:
            driver.ExecuteScript("mobile:application:close", params1);


            return true;
        }
        catch (Exception e)
        {
            System.Console.WriteLine("Failed to clean application cache");
            return false;
        }
    }
*/
}
