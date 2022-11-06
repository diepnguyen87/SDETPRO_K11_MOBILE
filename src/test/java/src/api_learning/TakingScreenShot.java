package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.internal.CapabilityHelpers;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.page_object.HomePO;
import src.page_object.LoginPO;

import java.io.File;

public class TakingScreenShot {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.Android);
        HomePO homePage = new HomePO(appiumDriver);

        try{
            //navigate to login screen
            homePage.navToLoginPage();
            LoginPO loginPage = new LoginPO(appiumDriver);

            //take whole screen
            File base64ScreenshotData = appiumDriver.getScreenshotAs(OutputType.FILE);
            String fileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginScreen.png");
            FileUtils.copyFile(base64ScreenshotData, new File(fileLocation));

            //take an area
            MobileElement loginFormElem = appiumDriver.findElement(MobileBy.AccessibilityId("Login-screen"));
            File base64LoginFormData = loginFormElem.getScreenshotAs(OutputType.FILE);
            String loginFormfileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginFormScreen.png");
            FileUtils.copyFile(base64LoginFormData, new File(loginFormfileLocation));

            //take an element
            MobileElement loginBtnElem = appiumDriver.findElement(MobileBy.AccessibilityId("button-LOGIN"));
            File base64LoginBtnData = loginBtnElem.getScreenshotAs(OutputType.FILE);
            String loginBtnfileLocation = System.getProperty("user.dir").concat("/screenshots/").concat("loginButtonScreen.png");
            FileUtils.copyFile(base64LoginBtnData, new File(loginBtnfileLocation));

            //Detect platform
            Capabilities caps = appiumDriver.getCapabilities();
            String platform = CapabilityHelpers.getCapability(caps,"platformName", String.class);
            System.out.println("Current platform: " + platform);

        }catch (Exception e){
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
