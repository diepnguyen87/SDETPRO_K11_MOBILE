package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import driver.DriverFactory;
import driver.Platform;
import page_object.FormsPO;
import page_object.HomePO;

public class FormsTest {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        HomePO homePage = new HomePO(appiumDriver);
        homePage.navToFormsPage();
        FormsPO formPage = new FormsPO(appiumDriver);

        try {
            //input field
            formPage.inputField("Nguyen Hong Diep");

            //switch on/off
            formPage.switchs("OFF");

            //Dropdown
            formPage.selectItemInDropdown("Appium is awesome");

            //Click Active button
            formPage.clickActiveBtn();

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }
}
