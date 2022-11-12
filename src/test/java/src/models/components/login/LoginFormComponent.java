package src.models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import src.models.components.global.BottomNavCommponent;

public class LoginFormComponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By emailSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public MobileElement emailElem(){
        return appiumDriver.findElement(emailSel);
    }

    public static MobileElement passwordElem(){
        return appiumDriver.findElement(passwordSel);
    }

    public MobileElement loginBtnElem(){
        return appiumDriver.findElement(loginBtnSel);
    }

    public BottomNavCommponent bottomNavCommponent(){
        return new BottomNavCommponent(appiumDriver);
    }

}
