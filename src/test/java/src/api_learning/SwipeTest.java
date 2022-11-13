package src.api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.locators.SwipeUI;
import src.page_object.HomePO;
import src.page_object.SwipePO;

import java.util.List;

public class SwipeTest {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.Android);
        HomePO homePage = new HomePO(appiumDriver);

        try {
            homePage.navToSwipePage();
            SwipePO swipePage = new SwipePO(appiumDriver);

            //get total card
            List<MobileElement> cardList = swipePage.findElementsByXpath(appiumDriver, SwipeUI.TOTAL_CARD);

            //Swipe to extendable card
            swipePage.swipeToCard(SwipeUI.EXTENDABLE_CARD, cardList.size(), 70, 10, 70, 70);
            swipePage.clickToCard(SwipeUI.EXTENDABLE_CARD);
            Thread.sleep(3000);
            swipePage.backToPreviousPage();
            Thread.sleep(3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
        appiumDriver.quit();
    }
}
