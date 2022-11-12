package src.models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import src.models.components.global.BottomNavCommponent;

public class LoginFormComponentMode03 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By emailSel = MobileBy.AccessibilityId("input-email");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");

    public LoginFormComponentMode03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponentMode03 inputEmail(String email) {
        appiumDriver.findElement(emailSel).sendKeys(email);
        return this;
    }

    public LoginFormComponentMode03 inputPassword(String password) {
        appiumDriver.findElement(passwordSel).sendKeys(password);
        return this;
    }

    public LoginFormComponentMode03 clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

    public BottomNavCommponent bottomNavCommponent() {
        return new BottomNavCommponent(appiumDriver);
    }

}
