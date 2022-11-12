package src.models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class BottomNavCommponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    private static final By loginIconSel = MobileBy.AccessibilityId("Login");

    public BottomNavCommponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
    }

    public MobileElement loginIconElem(){
        return appiumDriver.findElement(loginIconSel);
    }

    //Method 2 | introduce main introduction method
    public void clickOnLoginIcon(){
        appiumDriver.findElement(loginIconSel).click();
    }
}
