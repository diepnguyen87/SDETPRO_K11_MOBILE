package tests.authen;

import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import test_data.model.LoginCred;
import test_data.utils.DataObjectBuilder;
import test_flows.authentication.LoginFlow;
import tests.BaseTest;

public class LoginTestWithBaseTest extends BaseTest {

    @TmsLink("Testcase-123")
    @Issue("Jira-321")
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
        String fileLocation = "/main/java/tests/gson/login.json";
        LoginCred[] loginCredentialData = DataObjectBuilder.buildDataObject(fileLocation, LoginCred[].class);
        return loginCredentialData;
    }
}
