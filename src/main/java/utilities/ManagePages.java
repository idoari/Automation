package utilities;


public class ManagePages extends Base {

    public static void initMenoraHW() {
        MenoraHW_intro = new pageObjects.Menora_HW.IntroScreen(mobileDriver);
        MenoraHW_permistionAsk = new pageObjects.Menora_HW.PermisionAsk(mobileDriver);
        MenoraHW_LocationReject = new pageObjects.Menora_HW.LocationReject(mobileDriver);
        MenoraHW_gotIt = new pageObjects.Menora_HW.GotItScreen(mobileDriver);
        menoraHW_somethingWrong = new pageObjects.Menora_HW.SomethingWrong(mobileDriver);
        menoraHW_offline = new pageObjects.Menora_HW.Offline(mobileDriver);
        MenoraHW_BTWelcome = new pageObjects.Menora_HW.BluetoothWelcome(mobileDriver);
        MenoraHW_Home = new pageObjects.Menora_HW.HomeTabMain(mobileDriver);
        MenoraHW_Actions = new pageObjects.Menora_HW.ActionsTabMain(mobileDriver);
        MenoraHW_Profile = new pageObjects.Menora_HW.UserProfile(mobileDriver);
        MenoraHW_AddProfilePicture = new pageObjects.Menora_HW.MenuAddProfilePicture(mobileDriver);
        MenoraHW_RoadServices = new pageObjects.Menora_HW.RoadServices(mobileDriver);
        MenoraHW_Notifications = new pageObjects.Menora_HW.Notifications(mobileDriver);
        MenoraHW_AutoRenewal = new pageObjects.Menora_HW.AutoRenewalPackage(mobileDriver);
        MenoraHW_ViewPolicy = new pageObjects.Menora_HW.ViewPolicyDocuments(mobileDriver);
        MenoraHW_MenuCarPolicy = new pageObjects.Menora_HW.MenuWatchPolicyCarSelect(mobileDriver);
        MenoraHW_QnA = new pageObjects.Menora_HW.QnA(mobileDriver);
        MenoraHW_CustomerService = new pageObjects.Menora_HW.CustomerService(mobileDriver);
        MenoraHW_CustomerServiceClosed = new pageObjects.Menora_HW.CustomerServiceClosed(mobileDriver);
        MenoraHW_Insurance = new pageObjects.Menora_HW.InsuranceTabMain(mobileDriver);
        MenoraHW_LoadPackage = new pageObjects.Menora_HW.LoadPackage(mobileDriver);
        MenoraHW_CreditCardEdit = new pageObjects.Menora_HW.LoadPackageCreditCardEdit(mobileDriver);
        MenoraHW_Payments = new pageObjects.Menora_HW.LoadPackagePayments(mobileDriver);
        MenoraHW_UpdatedCredit = new pageObjects.Menora_HW.LoadPackageUpdatedCredit(mobileDriver);
        MenoraHW_LocationPopup = new pageObjects.Menora_HW.LocationPleaseAllowPopup(mobileDriver);
        MenoraHW_LocationHaveToAccept = new pageObjects.Menora_HW.LocationHaveToAccept(mobileDriver);




        //webview




    }


}
