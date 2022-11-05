package src.commons;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;

import java.util.List;

public class CommonFunctions {

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
