package driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DriverFactory implements MobileCapabilityTypeEx, AppPackage {

    private AppiumDriver<MobileElement> appiumDriver;

    public AppiumDriver<MobileElement> getDriver(Platform platform, String udid, String systemPort, String platformVersion) {
        String remoteInfoViaEnvVar = System.getenv("remote");
        String remoteInfoViaCommandVar = System.getProperty("remote");
        String isRemote = remoteInfoViaEnvVar == null ? remoteInfoViaCommandVar : remoteInfoViaEnvVar;

        String targetServer = "http://localhost:4723/wd/hub";
        if(isRemote.equals("true")){
            String hubIPAddress = System.getenv("hub");
            if (hubIPAddress == null) {
                hubIPAddress = System.getProperty("hub");
            }

            if(hubIPAddress == null){
                throw new IllegalArgumentException("Please provide hub ip address via env variable [hub]!");
            }

            targetServer = hubIPAddress + ":4545/wd/hub";
        }

        if(isRemote == null){
            throw new IllegalArgumentException("Please provide remote variable [remote]!");
        }

        if (appiumDriver == null) {
            URL appiumServer = null;
            try {
                appiumServer = new URL(targetServer);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (appiumServer == null) {
                throw new RuntimeException("[ERR] Somehow, we couldn't construct Appium server URL");
            }

            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            desiredCaps.setCapability(PLATFORM_NAME, platform);

            switch (platform) {
                case ios:
                    desiredCaps.setCapability(AUTOMATION_NAME, "XCUITest");
                    desiredCaps.setCapability(DEVICE_NAME, udid);
                    desiredCaps.setCapability(PLATFORM_VERSION, platformVersion);
                    desiredCaps.setCapability(BUNDLE_ID, "org.wdioNativeDemoApp");
                    desiredCaps.setCapability(WDA_LOCAL_PORT, systemPort);

                    appiumDriver = new IOSDriver<>(appiumServer, desiredCaps);
                    break;
                case android:
                    desiredCaps.setCapability(AUTOMATION_NAME, "uiautomator2");
                    desiredCaps.setCapability(UDID, udid);
                    desiredCaps.setCapability(APP_PACKAGE, WEBDRIVER_IO);
                    desiredCaps.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");
                    desiredCaps.setCapability(SYSTEM_PORT, systemPort);

                    appiumDriver = new AndroidDriver<>(appiumServer, desiredCaps);
                    break;
                default:
                    throw new IllegalArgumentException("Platform type can not be null");
            }
            appiumDriver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        }
        return appiumDriver;
    }

    public static AppiumDriver<MobileElement> getDriver(Platform platform) {
        AppiumDriver<MobileElement> driver = null;

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(PLATFORM_NAME, "android");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "uiautomator2");
        desiredCapabilities.setCapability(UDID, "MJSKLVNJAUDEPRV8");
        desiredCapabilities.setCapability(APP_PACKAGE, WEBDRIVER_IO);
        desiredCapabilities.setCapability(APP_ACTIVITY, "com.wdiodemoapp.MainActivity");

       /* desiredCapabilities.setCapability(PLATFORM_NAME, "ios");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 8 Plus");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.3.1");
        desiredCapabilities.setCapability(AUTOMATION_NAME, "XCUITest");
        desiredCapabilities.setCapability(UDID, "faca7d88838626eb39015a14dec85cbd2a027e9a");
        desiredCapabilities.setCapability(BUNDLE_IDENTIFIER, "org.wdioNativeDemoApp");

        String location = System.getProperty("user.dir").concat("/tests/resources/wdioNativeDemoApp.app");
        System.out.println("Locatin: " + location);*/
        //desiredCapabilities.setCapability(APP, "/Volumes/Data/Appium/SDETPRO_K11_MOBILE/tests/resources/wdioNativeDemoApp.app");

        URL appiumServer = null;
        try {
            appiumServer = new URL("http://localhost:4723/wd/hub");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (appiumServer == null) {
            throw new RuntimeException("[ERR] Somehow, we couldn't construct Appium server URL");
        }

        switch (platform) {
            case ios:
                driver = new IOSDriver<>(appiumServer, desiredCapabilities);
                break;
            case android:
                driver = new AndroidDriver<>(appiumServer, desiredCapabilities);
                break;
            default:
                throw new IllegalArgumentException("Platform type can not be null");
        }
        driver.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
        return driver;
    }

    public void quitAppiumSession(){
        if(appiumDriver != null){
            appiumDriver.quit();
        }
    }
}
