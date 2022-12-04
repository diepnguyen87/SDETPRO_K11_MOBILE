package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import driver.AppPackage;
import driver.DriverFactory;
import driver.Platform;

public class HandleMultipleApps {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        try {
            //Navigate to login formm
            MobileElement navLoginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login"));
            navLoginBtnElem.click();

            //Find login form elements
            MobileElement emailInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-email"));
            MobileElement passwordInputElem = appiumDriver.findElement(MobileBy.AccessibilityId("input-password"));
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));

            //Interact with login form (fill userName and password)
            emailInputElem.sendKeys("teo@sth.com");
            passwordInputElem.sendKeys("12345678");
            loginBtnElem.click();

            //Run app in background
            //appiumDriver.runAppInBackground(Duration.ofSeconds(3));

            //Open Setting app
            appiumDriver.activateApp(AppPackage.SETTINGS);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='Wi-Fi']")).click();
            boolean isWifiOff = appiumDriver.findElements(MobileBy.xpath("//*[@text='WI-FI NETWORKS']")).isEmpty();
            int timeToToggle = isWifiOff ? 1 : 2;

            MobileElement toggleElem = appiumDriver.findElement(MobileBy.id("com.coloros.wirelesssettings:id/switchWidget"));
            for (int toggleTime = 0; toggleTime < timeToToggle; toggleTime++) {
                toggleElem.click();
            }

            //Comeback to the main app
            appiumDriver.activateApp(AppPackage.WEBDRIVER_IO);
            appiumDriver.findElement(MobileBy.xpath("//*[@text='OK']")).click();

            //Debug purpose only
            Thread.sleep(2000);

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
