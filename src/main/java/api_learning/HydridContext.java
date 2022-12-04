package api_learning;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import contexts.Contexts;
import contexts.WaitMoreThanOneContext;
import driver.DriverFactory;
import driver.Platform;
import page_object.HomePO;
import page_object.WebViewPO;

import java.util.ArrayList;
import java.util.List;

public class HydridContext {

    public static void main(String[] args) {
        AppiumDriver<MobileElement> appiumDriver = DriverFactory.getDriver(Platform.android);
        HomePO homePage = new HomePO(appiumDriver);
        homePage.navToWebViewPage();
        WebViewPO webviewPage = new WebViewPO(appiumDriver);

        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 5L);
            wait.until(new WaitMoreThanOneContext(appiumDriver));

            //print all available contexts
            for (String context : appiumDriver.getContextHandles()) {
                System.out.println(context);
            }

            //Switch webview context
            appiumDriver.context(Contexts.WEB_VIEW);
            WebElement navToogleBtnElem = appiumDriver.findElementByCssSelector(".navbar__toggle");
            navToogleBtnElem.click();
            List<MobileElement> menuItemsElem = appiumDriver.findElementsByCssSelector(".menu__list li a");
            if (menuItemsElem.isEmpty()) {
                throw new RuntimeException("[ERROR] There is no list item to test");
            }

            List<MenuItemData> menuItemDataList = new ArrayList<>();

            for (MobileElement menuItemElem : menuItemsElem) {
                String itemText = menuItemElem.getText();
                String itemHref = menuItemElem.getAttribute("href");
                if (itemText.isEmpty()) {
                    menuItemDataList.add(new MenuItemData("Github ", itemHref));
                } else {
                    menuItemDataList.add(new MenuItemData(itemText, itemHref));
                }
            }

            //Verify at here
            System.out.println(menuItemDataList);

            //Switch back native context
            appiumDriver.context(Contexts.NATIVE);
            appiumDriver.findElement(MobileBy.AccessibilityId("Home")).click();

        } catch (Exception e) {
            e.printStackTrace();
        }

        appiumDriver.quit();
    }

    public static class MenuItemData{

        private final String name;
        private final String href;

        public String getName() {
            return name;
        }

        public String getHref() {
            return href;
        }

        public MenuItemData(String name, String href) {
            this.name = name;
            this.href = href;
        }

        @Override
        public String toString() {
            return "MenuItemData{" +
                    "name='" + name + '\'' +
                    ", href='" + href + '\'' +
                    '}';
        }
    }
}

