package src;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class AppiumLaunchTest {

    public static void main(String[] args) throws InterruptedException {
        AppiumDriver<MobileElement> driver = null;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("udid", "32009a815a33c5e1");
        desiredCapabilities.setCapability("appPackage", "com.wdiodemoapp");
        desiredCapabilities.setCapability("appActivity", "com.wdiodemoapp.MainActivity");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        }catch (Exception e){
            e.printStackTrace();
        }

        if(appiumServer == null){
            throw new RuntimeException("[ERR] Somehow, we couldn't construct Appium server URL");
        }
        driver = new AndroidDriver<>(appiumServer, desiredCapabilities);

        //DEBUG PURPOSE ONLY
        Thread.sleep(3000);

        //driver.quit();
    }
}
