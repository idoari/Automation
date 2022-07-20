package pageObjects.Youtube;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.support.PageFactory;

public class homepage {

    private AppiumDriver mobileDriver;

    public homepage(AppiumDriver mobileDriver) {
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
    }

    @AndroidFindBy(accessibility = "521")
    @iOSXCUITFindBy(accessibility = "521")
    public MobileElement btn_userInfo;

    @AndroidFindBy(accessibility = "526")
    @iOSXCUITFindBy(accessibility = "526")
    public MobileElement btn_CustomerService;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement lb_title;

    @AndroidFindBy(accessibility = "0.527.31")
    @iOSXCUITFindBy(accessibility = "0.527.31")
    public MobileElement btn_notification;

    @AndroidFindBy(accessibility = "1.527.34.33")
    @iOSXCUITFindBy(accessibility = "1.527.34.33")
    public MobileElement btn_biometricEnable;

    @AndroidFindBy(accessibility = "2.527.31")
    @iOSXCUITFindBy(accessibility = "2.527.31")
    public MobileElement btn_packageAutoLoad;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement lb_policy;

    @AndroidFindBy(accessibility = "0.527.31")
    @iOSXCUITFindBy(accessibility = "0.527.31")
    public MobileElement btn_packageLoad;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_watchPolicy;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_addCasualDriver;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement lb_service;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_roadServices;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_garageFind;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_appraiserFind;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_extraServices;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement lb_claims;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_fileClaim;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_claimStatus;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement lb_about;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_about;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_faq;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_privacyPolicy;

    @AndroidFindBy(accessibility = "528")
    @iOSXCUITFindBy(accessibility = "528")
    public MobileElement btn_accessibility;


}
