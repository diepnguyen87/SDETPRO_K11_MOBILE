package src.models.components.login;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import io.qameta.allure.Step;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.models.components.global.BottomNavCommponent;

import java.time.Duration;

public class LoginFormComponentMode03 {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By emailSel = MobileBy.AccessibilityId("input-email");
    //private static final By incorrectEmailTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter a valid email address')]");
    private static final By passwordSel = MobileBy.AccessibilityId("input-password");
    //private static final By incorrectPasswordTxtSel = MobileBy.xpath("//*[contains(@text, 'Please enter at least 8 characters')]");
    private static final By loginBtnSel = MobileBy.AccessibilityId("button-LOGIN");
    //private static final By loginSuccessAlertTitleSel = MobileBy.id("android:id/alertTitle");
    //private static final By loginSuccessAlertMessageSel = MobileBy.id("android:id/message");
    //private static final By okBtnSel = MobileBy.id("android:id/button1");

    public LoginFormComponentMode03(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)), this);
    }

    @Step("input email as {email}")
    public LoginFormComponentMode03 inputEmail(String email) {
        MobileElement emailElem = appiumDriver.findElement(emailSel);
        emailElem.clear();
        emailElem.sendKeys(email);
        return this;
    }

    @AndroidFindBy(xpath = "//*[contains(@text, 'Please enter a valid email address')]")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Please enter a valid email address\"")
    private MobileElement incorrectEmailTxtElem;

    public String invalidEmailTxt(){
        return incorrectEmailTxtElem.getText().trim();
    }

    @Step("input password as {password}")
    public LoginFormComponentMode03 inputPassword(String password) {
        MobileElement passwordElem = appiumDriver.findElement(passwordSel);
        passwordElem.clear();
        passwordElem.sendKeys(password);
        return this;
    }

    @AndroidFindBy(xpath = "//*[contains(@text, 'Please enter at least 8 characters')]")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Please enter at least 8 characters\"")
    private MobileElement incorrectPasswordxtElem;

    public String invalidPasswordTxt(){
        return incorrectPasswordxtElem.getText().trim();
    }

    public LoginFormComponentMode03 clickOnLoginBtn() {
        appiumDriver.findElement(loginBtnSel).click();
        return this;
    }

    @AndroidFindBy(id = "android:id/alertTitle")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Success\"")
    private MobileElement loginSuccessAlertTitleSel;

    public String getAlertTitle(){
        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(loginSuccessAlertTitleSel));
        wait.until(ExpectedConditions.visibilityOf(loginSuccessAlertTitleSel));
        return loginSuccessAlertTitleSel.getText();
    }

    @AndroidFindBy(id = "android:id/message")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"You are logged in!\"")
    private MobileElement loginSuccessAlertMessageSel;

    public String getAlertMessage(){
        return loginSuccessAlertMessageSel.getText();
    }

    public BottomNavCommponent bottomNavCommponent() {
        return new BottomNavCommponent(appiumDriver);
    }

    @AndroidFindBy(id = "android:id/button1")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"OK\"")
    private MobileElement okBtnSel;

    @Step("Click on Login button")
    public void clickOnOKBtn() {
        okBtnSel.click();
    }
}
