package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavCommponent;
import models.components.login.LoginFormComponentMode03;

public class LoginScreenMode03 {

    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginScreenMode03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponentMode03 loginFormComponent(){
        return new LoginFormComponentMode03(appiumDriver);
    }

    public BottomNavCommponent bottomNavCommponent(){
        return new BottomNavCommponent(appiumDriver);
    }

}
