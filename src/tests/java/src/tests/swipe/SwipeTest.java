package src.tests.swipe;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import src.driver.DriverFactory;
import src.driver.Platform;
import src.locators.SwipeUI;
import src.page_object.HomePO;
import src.page_object.SwipePO;
import src.tests.BaseTest;

import java.util.List;

public class SwipeTest extends BaseTest {

    @Test
    public void swipeTest() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();
        HomePO homePage = new HomePO(appiumDriver);

        homePage.navToSwipePage();
        SwipePO swipePage = new SwipePO(appiumDriver);

        //get total card
        List<MobileElement> cardList = swipePage.findElementsByXpath(appiumDriver, SwipeUI.TOTAL_CARD);

        //Swipe to extendable card
        swipePage.swipeToCard(SwipeUI.EXTENDABLE_CARD, cardList.size(), 70, 10, 70, 70);
        swipePage.clickToCard(SwipeUI.EXTENDABLE_CARD);
        try {
            Thread.sleep(3000);
            swipePage.backToPreviousPage();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.fail("ERROR ...");
    }
}
