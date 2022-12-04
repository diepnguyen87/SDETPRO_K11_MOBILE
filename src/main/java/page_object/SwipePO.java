package page_object;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import commons.CommonFunctions;

import java.time.Duration;
import java.util.List;

public class SwipePO extends CommonFunctions {

    private AppiumDriver<MobileElement> driver;

    public SwipePO(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public void swipeToCard(String cardXpath, int totalCard, int xStart, int xEnd, int yStart, int yEnd) {
        PointOption[] pointOptions = calculateSwipeCoordinates(driver, xStart, xEnd, yStart, yEnd);
        PointOption startPoint = pointOptions[0];
        PointOption endPoint = pointOptions[1];

        TouchAction touchAction = new TouchAction(driver);

        for (int i = 0; i < totalCard; i++) {
            MobileElement element = findElementByXpath(driver, cardXpath);
            if(element != null){
                System.out.println("The card is FOUND");
                return;
            }
            touchAction
                    .press(startPoint)
                    .waitAction(new WaitOptions().withDuration(Duration.ofMillis(500)))
                    .moveTo(endPoint)
                    .release()
                    .perform();
        }
        System.out.println("The card does NOT exist");
    }

    private int getNumOfCard(String xpath){
        List<MobileElement> elementList = findElementsByXpath(driver, xpath);
        return elementList.size();
    }

    public void clickToCard(String extendableCard) {
        MobileElement card = findElementByXpath(driver, extendableCard);
        card.click();
    }

    public void backToPreviousPage() {
        ((AndroidDriver)driver).pressKey(new KeyEvent(AndroidKey.BACK));
    }
}
