package models.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavCommponent;
import models.components.login.LoginFormComponent;

public class LoginScreenMode01 {

    private final AppiumDriver<MobileElement> appiumDriver;

    public LoginScreenMode01(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponent loginFormComponent(){
        return new LoginFormComponent(appiumDriver);
    }

    public BottomNavCommponent bottomNavCommponent(){
        return new BottomNavCommponent(appiumDriver);
    }

}
