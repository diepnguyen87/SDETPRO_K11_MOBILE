package src.tests.authen;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.annotations.Test;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.test_data.model.LoginCred;
import src.test_flows.authentication.LoginFlow;

import java.util.ArrayList;
import java.util.List;

public class LoginTest {

    @Test
    public void TestLogin(){
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.Android);
        try {

            for (LoginCred loginCredentialDatum : loginCredDataSet()) {
                String email = loginCredentialDatum.getEmail();
                String password = loginCredentialDatum.getPassword();
                LoginFlow loginFlow = new LoginFlow(appiumDriver, email, password);
                loginFlow.goToLoginScreen();
                loginFlow.login();
                loginFlow.verifyLogin();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    private List<LoginCred> loginCredDataSet(){
        List<LoginCred> loginCredentialData = new ArrayList<>();
        loginCredentialData.add(new LoginCred("", ""));
        loginCredentialData.add(new LoginCred("teo@st.com", "1"));
        loginCredentialData.add(new LoginCred("teo@", "12345678"));
        loginCredentialData.add(new LoginCred("teo@st.com", "12345678"));

        return loginCredentialData;
    }
}
