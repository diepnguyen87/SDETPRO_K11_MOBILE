package src.tests.authen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import lombok.extern.java.Log;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.test_data.model.LoginCred;
import src.test_data.utils.DataObjectBuilder;
import src.test_flows.authentication.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class LoginTestWithDataProvider {

    @Test(dataProvider = "loginData")
    public void TestLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.IOS);

        try {
            String email = loginCred.getEmail();
            String password = loginCred.getPassword();
            LoginFlow loginFlow = new LoginFlow(appiumDriver, email, password);
            loginFlow.goToLoginScreen();
            loginFlow.login();
            loginFlow.verifyLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    @DataProvider(name = "loginData")
    private LoginCred[] loginCredDataSet() {
        String fileLocation = "/src/tests/java/src/tests/gson/login.json";
        LoginCred[] loginCredentialData = DataObjectBuilder.buildDataObject(fileLocation, LoginCred[].class);
        return loginCredentialData;
    }
}
