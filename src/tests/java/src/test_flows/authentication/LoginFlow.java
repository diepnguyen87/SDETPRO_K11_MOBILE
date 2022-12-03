package src.test_flows.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Issue;
import org.apache.commons.validator.routines.EmailValidator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import src.models.components.login.LoginFormComponentMode03;
import src.models.pages.LoginScreenMode03;
import src.test_flows.BaseFlow;

public class LoginFlow extends BaseFlow {

    private String email;
    private String password;
    private SoftAssert softAssert;

    public LoginFlow(AppiumDriver<MobileElement> appiumDriver, String email, String password) {
        super(appiumDriver);
        this.email = email;
        this.password = password;
        softAssert = new SoftAssert();
    }

    public void login(){
        LoginScreenMode03 loginScreen = new LoginScreenMode03(appiumDriver);
        LoginFormComponentMode03 loginFormComponentMode03 = loginScreen.loginFormComponent();

        if(!email.isEmpty()){
            loginFormComponentMode03.inputEmail(email);
        }
        if (!password.isEmpty()) {

            loginFormComponentMode03.inputPassword(password);
        }
        loginFormComponentMode03.clickOnLoginBtn();
    }

    public void verifyLogin(){
        boolean isEmailValid = isEmailValid();
        boolean isPasswordValid = isPasswordValid();

        LoginFormComponentMode03 loginFormComponent = new LoginScreenMode03(appiumDriver).loginFormComponent();

        if(isEmailValid && isPasswordValid){
            verifyCorrectLoginCredential(loginFormComponent);
            closeSuccessPopup(loginFormComponent);
        }

        if(!isEmailValid){
            verifyIncorrectEmailLogin(loginFormComponent);
        }

        if(!isPasswordValid){
            verifyIncorrectPasswordLogin(loginFormComponent);
        }
        softAssert.assertAll();
    }

    private boolean isEmailValid() {
        return EmailValidator.getInstance().isValid(email);
    }

    private boolean isPasswordValid() {
        return password.length() >= 8;
    }

    private void verifyCorrectLoginCredential(LoginFormComponentMode03 loginForm) {

        String actualSuccessAlertTitle = loginForm.getAlertTitle();
        String expectedSuccessAlertTitle = "Success";
        softAssert.assertEquals(actualSuccessAlertTitle, expectedSuccessAlertTitle);

        String actualSuccessAlertMessage = loginForm.getAlertMessage();
        String expectedSuccessAlertMessage = "You are logged in!";
        softAssert.assertEquals(actualSuccessAlertMessage, expectedSuccessAlertMessage);
    }

    private void closeSuccessPopup(LoginFormComponentMode03 loginForm) {
        loginForm.clickOnOKBtn();
    }


    private void verifyIncorrectEmailLogin(LoginFormComponentMode03 loginForm) {
        String actualInvalidEmailTxt = loginForm.invalidEmailTxt();
        String expectedInvalidEmailTxt = "Please enter a valid email address";
        softAssert.assertEquals(actualInvalidEmailTxt, expectedInvalidEmailTxt);
    }

    private void verifyIncorrectPasswordLogin(LoginFormComponentMode03 loginForm) {
        String actualInvalidPasswordTxt = loginForm.invalidPasswordTxt();
        String expectedInvalidPasswordTxt = "Please enter at least 8 characters";
        softAssert.assertEquals(actualInvalidPasswordTxt, expectedInvalidPasswordTxt);
    }

}
