package src.test_flows;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.models.pages.LoginScreenMode03;

public class BaseFlow {

    protected final AppiumDriver<MobileElement> appiumDriver;

    public BaseFlow(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public void goToLoginScreen(){
        new LoginScreenMode03(appiumDriver).bottomNavCommponent().clickOnLoginIcon();
    }
}
