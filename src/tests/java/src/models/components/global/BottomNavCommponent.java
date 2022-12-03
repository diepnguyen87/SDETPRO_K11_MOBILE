package src.models.components.global;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class BottomNavCommponent {

    private final AppiumDriver<MobileElement> appiumDriver;
    //private static final By loginIconSel = MobileBy.AccessibilityId("Login");

    public BottomNavCommponent(AppiumDriver<MobileElement> appiumDriver) {
        this.appiumDriver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver, Duration.ofSeconds(10)), this);
    }

    @AndroidFindBy(accessibility = "Login")
    @iOSXCUITFindBy(iOSNsPredicate = "label == \"Login\"")
    private MobileElement loginIconSel;

    public MobileElement loginIconElem(){
        return loginIconSel;
    }

    //Method 2 | introduce main introduction method
    public void clickOnLoginIcon(){
        loginIconSel.click();
    }
}
