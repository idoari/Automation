package utilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.sikuli.script.Screen;
import org.testng.asserts.SoftAssert;
import workflows.MobileFlows;
import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class CommonOps extends Base {

    @BeforeClass
    @Parameters({"PlatformName"})
    public void startSession(String PlatformName) throws IOException {
        platform=PlatformName;
         //if (platform.equalsIgnoreCase("api"))
        initAPI();
        //may use db in any platform -so initialize to all
        //ManageDB.StartConnection(getData("db_URL"),getData("db_User"),getData("db_Pass"));
        //softAssert = new SoftAssert();
        //screen = new Screen();

        //config file
        prop = new Properties();
        prop.load(new FileInputStream(System.getProperty("user.dir") + "/Configuration/config.properties" ));
		MobileFlows.ReadAccounts(); //read from file the id and phone numbers for the accounts todo:replace with db
    }
/*
    @AfterClass
    public void closeSession () {
        //ManageDB.CloseConnection();
    }
 */

    @BeforeMethod
    public void beforeMethod(Method method) {
        if (!platform.equalsIgnoreCase("api")) {

            try {
                monteScreenRecorder.startRecord(method.getName());
            } catch (Exception e) {
                System.out.println("error occur while screen record" + e) ;
            }


        }
    }

    private static void initAPI() {
        //RestAssured.baseURI = getData("API_BASE");
        //httpRequest = RestAssured.given().auth().preemptive().basic(getData("API_USERNAME"),getData("API_PASSWORD"));
    }


    public static void initBrowser (String browserType) {
        if (browserType.equalsIgnoreCase("chrome"))
            driver = initChromeDriver() ;
        else if (browserType.equalsIgnoreCase("firefox"))
            driver = initFirefoxDriver() ;
        else if (browserType.equalsIgnoreCase("ie"))
            driver = initIEDriver() ;
        else
            throw new RuntimeException("invalid browser type");

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,2);
        driver.get("https://friends.walla.co.il/login");
        //ManagePages.initOranageHRM(); //initialize the page objected with pagefactory
        action = new Actions(driver);

    }

    public static WebDriver initChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public static WebDriver initFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        return driver;
    }

    public static WebDriver initIEDriver() {
        WebDriverManager.iedriver().setup();
        WebDriver driver = new InternetExplorerDriver();
        return driver;
    }


    public static AndroidDriver initMobileAndroid(int port) { //compatible with native, hybrid and mobile browser testing
        //when you run an emulator , defined emulator name as "emulator-X" to differentiate between real device and emulators
        //get device udid : adb devices

        //example of maven run command - activated from Jenkins
        //**real device**
        //mvn -Dudid=ce051605bd453d2502 -Demulator=false -DrunPlatform=Android -Dname=SM-G930F -Pmobile test
        //**emulator**
        //mvn -Dudid= -Demulator=true -DrunPlatform=Android -Dname=ido -Pmobile test
        String udid = System.getProperty("udid"); //use this command to send to maven the device name in every run : mvn test -DUDID=iphone
        String deviceName = System.getProperty("name");
        deviceName= deviceName.replace("_" , " ");
        boolean isEmulator = Boolean.valueOf(System.getProperty("emulator"));
        String runPlatform = System.getProperty("runPlatform"); // IOS\Android

        String app= System.getProperty("user.dir") + "/AUT/"; // get the app directory
        app = app + prop.get("appNameAndroid"); //app path for ios

        DesiredCapabilities dc = new DesiredCapabilities();

        if (isEmulator) { //start the emulator when the device is emulator
            startEmulatorDevice(runPlatform,deviceName);
        }
        dc.setCapability("deviceName",deviceName); //either emulator and real have to have name
        dc.setCapability(MobileCapabilityType.UDID, udid);
        dc.setCapability(MobileCapabilityType.PLATFORM_NAME,runPlatform.toUpperCase());
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"uiAutomator2");
        dc.setCapability(MobileCapabilityType.APP, app); //if you have the apk you can use this line instead of the two above
        dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,50000); //TIMEOUT FOR MOBILE ACTION - UPPER LIMIT\
        dc.setCapability(MobileCapabilityType.FULL_RESET, true);
        dc.setCapability(MobileCapabilityType.NO_RESET,false);
        dc.setCapability("chromedriverExecutableDir","Drivers"); //set drivers directory as chromedriver dir

        //dc.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome"); //when testing mobile browser use this line

        AndroidDriver driver = null;

        try {
            String url = (String)(prop.get("AppiumServer"));
            url=url.replace("+port+",String.valueOf(port));
            driver = new AndroidDriver(new URL(url), dc);
            driver.manage().timeouts().implicitlyWait(Long.parseLong((String)prop.get("TimeOut")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver,Long.parseLong((String)prop.get("TimeOut")));
        } catch (Exception e) {
            System.out.println("error occurred : " + e);
        }
        return driver;
    }


    public static IOSDriver initMobileIOS(int port) { //compatible with native, hybrid and mobile browser testing
        //when you run an emulator , defined emulator name as "emulator-X" to differentiate between real device and emulators
        //IOS: type in terminal instruments -s devices to see all device version and type it in desire capabilities
        //IOS: use command sudo xcode-select -s /Applications/Xcode.app/Contents/Developer if not working

        //example of maven run command - activated from Jenkins
        //**real device**
        //mvn -Dudid=00008020-001405841E3A002E -Demulator=false -DrunPlatform=ios -Dname=iPhone_XR -Pmobile test
        //**emulator**
        //mvn -Dudid=F1B0DA5B-4E4A-4AEF-9E22-C3D127FFA0C0 -Demulator=true -DrunPlatform=ios -Dname=iPhone_8 -Pmobile test
        String udid = System.getProperty("udid"); //use this command to send to maven the device name in every run : mvn test -DUDID=iphone
        String deviceName = System.getProperty("name");
        deviceName= deviceName.replace("_" , " ");
        boolean isEmulator = Boolean.valueOf(System.getProperty("emulator"));
        String runPlatform = System.getProperty("runPlatform"); // IOS\Android

        String app= System.getProperty("user.dir") + "/AUT/"; // get the app directory
        app = app + prop.get("appNameIOS"); //app path for ios

        DesiredCapabilities dc = new DesiredCapabilities();

        if (isEmulator) { //start the emulator when the device is emulator
            startEmulatorDevice(runPlatform,udid); // to get udid for the emulators - xcrun simctl list
            //build the app with this command - xcodebuild build -workspace "*****.xcworkspace" -scheme "*****" -destination "platform=iOS Simulator,name=iPhone SE" -configuration Release
        } else { //real device
            dc.setCapability("udid",udid);
            dc.setCapability("xcodeOrgId", prop.get("TeamID")); //get it when open dev account on "developer.apple.com/account"
            dc.setCapability("xcodeSigningId", "iPhone Developer");
            dc.setCapability("updatedWDABundleId", prop.get("BundleID"));
        }

        dc.setCapability("deviceName",deviceName); //either emulator and real have to have names
        dc.setCapability("automationName", "XCUITest");
        dc.setCapability("platformName", runPlatform.toUpperCase());
        dc.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,50000); //TIMEOUT FOR MOBILE ACTION - UPPER LIMIT\
        dc.setCapability(MobileCapabilityType.APP, app);


        //dc.setCapability(MobileCapabilityType.APP, "com.apple.stocks");
        IOSDriver driver = null;
        try {
            String url = (String)(prop.get("AppiumServer"));
            url = url.replace("+port+",String.valueOf(port));
            driver = new IOSDriver<>(new URL(url), dc);
            driver.manage().timeouts().implicitlyWait(Long.parseLong((String) prop.get("TimeOut")), TimeUnit.SECONDS);
            wait = new WebDriverWait(driver, Long.parseLong((String) prop.get("TimeOut")));
        } catch(Exception e) {
            System.out.println("error occurred : " + e);
        }
        return driver;
    }

    public static void startEmulatorDevice(String platform ,String udidOrName)  {
         if (platform.equalsIgnoreCase("ios")) //ios device
         {
             try {
                 Runtime.getRuntime().exec("open -a Simulator --args -CurrentDeviceUDID " + udidOrName);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
         else { //android device
             try {
                 Runtime.getRuntime().exec("cd $ANDROID_HOME/emulator && eEmulator -avd " + udidOrName); // && run the second command when the first run successfully
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
        try {Thread.sleep(20000); // wait for emulator to start
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int findAvailableAppiumPort()  {
        int[] range = IntStream.rangeClosed(8200, 8299).toArray();
        ServerSocket serverSocket;
        for (int port : range) {
            try {
                serverSocket= new ServerSocket(port);
                serverSocket.close();
                return port;
            } catch (IOException ex) {
                continue; // try next port
            }
        }
        // if the program gets here, no port in the range was found
        return -1;
    }


    public static AppiumDriverLocalService startAppium(int port) throws IOException {
        killAppium(port); //kill all appium servers
        //killProcess("port "+ port); //kill all appium servers
        //killProcess("Android/sdk/"); //kill all appium servers
        //illProcess("chromedriver");
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withArgument(() -> "--allow-insecure","chromedriver_autodownload");
        builder.withIPAddress("127.0.0.1").usingPort(port);
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        return service;
    }

    public static void closeAppium() { //exit appium when test is over
        try {
            service.stop();
        }catch (Exception e) {
            System.out.println("appium already stopped");
        }
    }

    public static void killAppium(int port) throws IOException {
        String cmd;
        String line;

        if (System.getProperty("os.name").contains("Mac")) { //if it's MAC
            //cmd = "netstat -vanp tcp | grep " + port; //get the running apps path and PID
            cmd="ps -ax";
            List<String> str = new ArrayList<>();

            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            //extract pid
            while ((line = reader.readLine()) != null) {
                if (line.contains("port "+ port) ) {
                    str.add(line.split(" ")[0]); //get the relevant PID
                }
            }
            //kill processes
            for (String proc : str) {
                System.out.println("Killing Appium...");
                Runtime.getRuntime().exec("kill -9 " + proc.trim()); //kill the appium processes by PID
            }
            reader.close();


        } else if (System.getProperty("os.name").contains("Windows")) {
            cmd ="taskkill /F /IM node.exe";
            Runtime.getRuntime().exec(cmd);
        }
    }
}
