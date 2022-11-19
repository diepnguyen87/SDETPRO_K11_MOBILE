package src.commons;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CommonFunctions {

    private WebDriverWait explicitWait;
    private static final long SHORT_TIME = 5L;
    private static final long LONG_TIME = 10L;

    public MobileElement findElementByXpath(AppiumDriver<MobileElement> driver, String xpath) {

        MobileElement element = null;
        try {
            element = driver.findElement(MobileBy.xpath(xpath));
        } catch (Exception e) {
            return null;
        }
        return element;
    }

    public List<MobileElement> findElementsByXpath(AppiumDriver<MobileElement> driver, String xpath) {

        List<MobileElement> element = null;
        try {
            element = driver.findElements(MobileBy.xpath(xpath));
        } catch (Exception e) {
            return null;
        }
        return element;
    }

    public void waitToElementVisible(AppiumDriver<MobileElement> driver, By locator){
        explicitWait = new WebDriverWait(driver, SHORT_TIME);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public PointOption[] calculateSwipeCoordinates(AppiumDriver<MobileElement> driver, int xStart, int xEnd, int yStart, int yEnd){
        //get mobile screen
        Dimension windowSize = driver.manage().window().getSize();
        int screenHeight = windowSize.getHeight();
        int screenWidth = windowSize.getWidth();

        //calculate touch points
        int xStartPoint = xStart * screenWidth / 100;
        int xEndPoint = xEnd * screenWidth / 100;
        int yStartPoint = yStart * screenHeight / 100;
        int yEndPoint = yEnd * screenHeight / 100;

        //Convert point into coordinate
        PointOption startPoint = new PointOption().withCoordinates(xStartPoint, yStartPoint);
        PointOption endPoint = new PointOption().withCoordinates(xEndPoint, yEndPoint);

        return new PointOption[]{startPoint, endPoint};
    }
}
