package src.page_object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WebViewPO {

    private AppiumDriver<MobileElement> driver;

    public WebViewPO(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

}
