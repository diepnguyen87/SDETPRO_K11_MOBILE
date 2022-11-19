package src.page_object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import src.commons.CommonFunctions;

public class HomePO extends CommonFunctions {

    private AppiumDriver<MobileElement> driver;

    public HomePO(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void navToSwipePage() {
        MobileElement swipeBtnElem = driver.findElement(MobileBy.AccessibilityId("Swipe"));
        swipeBtnElem.click();
        waitToElementVisible(driver, MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Swipe horizontal\")"));
    }

    public void navToFormsPage() {
        MobileElement formsBtnElem = driver.findElement(MobileBy.AccessibilityId("Forms"));
        formsBtnElem.click();
        waitToElementVisible(driver, MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Form components\")"));
    }

    public void navToWebViewPage() {
        MobileElement webviewBtnElem = driver.findElement(MobileBy.AccessibilityId("Webview"));
        webviewBtnElem.click();
    }

    public void navToLoginPage() {
        MobileElement loginBtnElem = driver.findElement(MobileBy.AccessibilityId("Login"));
        loginBtnElem.click();
        waitToElementVisible(driver, MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"Sign up Form\")"));
    }
}
