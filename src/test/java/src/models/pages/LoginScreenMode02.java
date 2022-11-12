package src.models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.models.components.global.BottomNavCommponent;
import src.models.components.login.LoginFormComponentMode02;

public class LoginScreenMode02 {

    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginScreenMode02(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponentMode02 loginFormComponent(){
        return new LoginFormComponentMode02(appiumDriver);
    }

    public BottomNavCommponent bottomNavCommponent(){
        return new BottomNavCommponent(appiumDriver);
    }

}
