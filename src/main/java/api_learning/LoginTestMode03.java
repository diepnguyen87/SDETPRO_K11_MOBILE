package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactory;
import driver.Platform;
import models.components.login.LoginFormComponentMode03;
import models.pages.LoginScreenMode03;

public class LoginTestMode03 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            LoginScreenMode03 loginScreen = new LoginScreenMode03(appiumDriver);
            loginScreen.bottomNavCommponent().clickOnLoginIcon();

            LoginFormComponentMode03 loginFormComponent = loginScreen.loginFormComponent();
            loginFormComponent
                    .inputEmail("teo@sth.com")
                    .inputPassword("12345678")
                    .clickOnLoginBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
