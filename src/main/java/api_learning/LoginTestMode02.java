package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactory;
import driver.Platform;
import models.components.login.LoginFormComponentMode02;
import models.pages.LoginScreenMode02;

public class LoginTestMode02 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            LoginScreenMode02 loginScreen = new LoginScreenMode02(appiumDriver);
            loginScreen.bottomNavCommponent().clickOnLoginIcon();

            LoginFormComponentMode02 loginFormComponent = loginScreen.loginFormComponent();
            loginFormComponent.inputEmail("teo@sth.com");
            loginFormComponent.inputPassword("12345678");
            loginFormComponent.clickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
