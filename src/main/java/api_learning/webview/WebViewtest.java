package api_learning.webview;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import models.components.global.BottomNavCommponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import contexts.Contexts;
import contexts.WaitMoreThanOneContext;
import tests.BaseTest;

import java.util.ArrayList;
import java.util.List;

public class WebViewtest extends BaseTest {

    @Test
    public void testWebView() {
        AppiumDriver<MobileElement> appiumDriver = getDriver();

        /*HomePO homePage = new HomePO(appiumDriver);
        homePage.navToWebViewPage();
        WebViewPO webviewPage = new WebViewPO(appiumDriver);
*/
        BottomNavCommponent bottomNavCommponent = new BottomNavCommponent(appiumDriver);
        bottomNavCommponent.clickOnWebviewIcon();

        WebDriverWait wait = new WebDriverWait(appiumDriver, 10L);
        wait.until(new WaitMoreThanOneContext(appiumDriver));

        //print all available contexts
        String webviewContext = "";
        for (String contextName : appiumDriver.getContextHandles()) {
            System.out.println(contextName);
            if (contextName.startsWith("WEBVIEW_com.wdiodemoapp")){
                webviewContext = contextName;
            }
        }

        if(webviewContext.isEmpty()){
            throw new RuntimeException("[ERROR] - There is no context start with WEBVIEW");
        }

        //Switch webview context
        appiumDriver.context(webviewContext);
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
    }

    public static class MenuItemData {

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

