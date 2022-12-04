package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactory;
import driver.Platform;
import models.components.login.LoginFormComponent;
import models.pages.LoginScreenMode01;

public class LoginTestMode01 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            LoginScreenMode01 loginScreen = new LoginScreenMode01(appiumDriver);
            loginScreen.bottomNavCommponent().clickOnLoginIcon();

            LoginFormComponent loginFormComponent = loginScreen.loginFormComponent();
            loginFormComponent.emailElem().sendKeys("teo@sth.com");
            loginFormComponent.passwordElem().sendKeys("12345678");
            loginFormComponent.loginBtnElem().click();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
