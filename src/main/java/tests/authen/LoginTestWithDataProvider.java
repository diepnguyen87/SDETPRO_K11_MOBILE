package tests.authen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import driver.DriverFactory;
import driver.Platform;
import test_data.model.LoginCred;
import test_data.utils.DataObjectBuilder;
import test_flows.authentication.LoginFlow;

public class LoginTestWithDataProvider {

    @Test(dataProvider = "loginData")
    public void TestLogin(LoginCred loginCred) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.ios);

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
        String fileLocation = "/main/java/tests/gson/login.json";
        LoginCred[] loginCredentialData = DataObjectBuilder.buildDataObject(fileLocation, LoginCred[].class);
        return loginCredentialData;
    }
}
