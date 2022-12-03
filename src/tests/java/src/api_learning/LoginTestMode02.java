package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.models.components.login.LoginFormComponentMode02;
import src.models.pages.LoginScreenMode02;

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
