package page_object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginPO {

    private AppiumDriver<MobileElement> driver;

    public LoginPO(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

}
