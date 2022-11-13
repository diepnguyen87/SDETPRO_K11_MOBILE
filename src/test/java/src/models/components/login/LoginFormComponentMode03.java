package src.models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.models.components.global.BottomNavCommponent;

public class LoginFormComponentMode03 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By emailSel = MobileBy.AccessibilityId("input-email");
    private static final By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    private static final By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    private static final By loginSuccessAlertTitleSel = MobileBy.id("android:id/alertTitle");
    private static final By loginSuccessAlertMessageSel = MobileBy.id("android:id/message");

    public LoginFormComponentMode03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public LoginFormComponentMode03 inputEmail(String email) {
        MobileElement emailElem = appiumDriver.findElement(emailSel);
        emailElem.clear();
        emailElem.sendKeys(email);
        return this;
    }

    public String invalidEmailTxt(){
        return appiumDriver.findElement(incorrectEmailTxtSel).getText();
    }

    public LoginFormComponentMode03 inputPassword(String password) {
        MobileElement passwordElem = appiumDriver.findElement(passwordSel);
        passwordElem.clear();
        passwordElem.sendKeys(password);
        return this;
    }

    public String invalidPasswordTxt(){
        return appiumDriver.findElement(incorrectPasswordTxtSel).getText();
    }

    public LoginFormComponentMode03 clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

    public String getAlertTitle(){
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginSuccessAlertTitleSel));
        return appiumDriver.findElement(loginSuccessAlertTitleSel).getText();
    }

    public String getAlertMessage(){
        return appiumDriver.findElement(loginSuccessAlertMessageSel).getText();
    }

    public BottomNavCommponent bottomNavCommponent() {
        return new BottomNavCommponent(appiumDriver);
    }

}
