package page_object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import commons.CommonFunctions;

public class FormsPO extends CommonFunctions {

    private AppiumDriver<MobileElement> driver;

    public FormsPO(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void inputField(String value) {
        MobileElement inputElem = driver.findElement(MobileBy.AccessibilityId("text-input"));
        inputElem.sendKeys(value);
    }

    public void switchs(String value){
        MobileElement switchElem = driver.findElement(MobileBy.AccessibilityId("switch"));
        String mode = switchElem.getText();
        if(!mode.equalsIgnoreCase(value)){
            switchElem.click();
        }
    }

    public void selectItemInDropdown(String item) {
        MobileElement dropdownElem = driver.findElement(MobileBy.AccessibilityId("Dropdown"));
        dropdownElem.click();

        MobileElement dropdownItemElem = driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" + item + "\")"));
        dropdownItemElem.click();

    }

    public void clickActiveBtn() {
        MobileElement activeBtnElem = driver.findElement(MobileBy.AccessibilityId("button-Active"));
        activeBtnElem.click();

        MobileElement okBtnElem = driver.findElement(MobileBy.id("android:id/button1"));
        okBtnElem.click();
    }
}
