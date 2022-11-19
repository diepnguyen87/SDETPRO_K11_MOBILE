package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.models.components.login.LoginFormComponentMode03;
import src.models.pages.LoginScreenMode03;

public class LoginTestMode03 {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.Android);
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
