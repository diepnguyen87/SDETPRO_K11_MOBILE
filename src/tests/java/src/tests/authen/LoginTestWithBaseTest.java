package src.tests.authen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.test_data.model.LoginCred;
import src.test_data.utils.DataObjectBuilder;
import src.test_flows.authentication.LoginFlow;
import src.tests.BaseTest;

import java.util.Base64;

public class LoginTestWithBaseTest extends BaseTest {

    @Test(dataProvider = "loginData")
    public void TestLogin(LoginCred loginCred) {
        String email = loginCred.getEmail();
        String password = loginCred.getPassword();
        LoginFlow loginFlow = new LoginFlow(getDriver(), email, password);
        loginFlow.goToLoginScreen();
        loginFlow.login();
        loginFlow.verifyLogin();
        //Assert.fail("[ERROR]");
    }

    @DataProvider(name = "loginData")
    private LoginCred[] loginCredDataSet() {
        String fileLocation = "/src/tests/java/src/tests/gson/login.json";
        LoginCred[] loginCredentialData = DataObjectBuilder.buildDataObject(fileLocation, LoginCred[].class);
        return loginCredentialData;
    }
}
