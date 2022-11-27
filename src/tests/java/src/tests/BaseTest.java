package src.tests;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import src.driver.DriverFactory;
import src.driver.Platform;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class BaseTest {

    private List<DriverFactory> driverThreadPool = Collections.synchronizedList(new ArrayList<>());
    private static ThreadLocal<DriverFactory> driverThread;
    private String udid;
    private String systemPort;

    protected AppiumDriver<MobileElement> getDriver() {
        return driverThread.get().getDriver(Platform.Android, udid, systemPort);
    }

    @BeforeTest()
    @Parameters({"udid", "systemPort"})
    public void initAppiumSession(String udid, String systemPort) {
        this.udid = udid;
        this.systemPort = systemPort;
        driverThread = ThreadLocal.withInitial(() -> {
            DriverFactory driverthead = new DriverFactory();
            driverThreadPool.add(driverthead);
            return driverthead;
        });
    }

    @AfterTest(alwaysRun = true)
    public void quitAppiumSession() {
        driverThread.get().quitAppiumSession();
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            //1. Get method name
            String testMethodName = result.getName();

            //2. Get taken time | y-m-d-h-m-s
            Calendar calendar = new GregorianCalendar();
            int y = calendar.get(Calendar.YEAR);
            int m = calendar.get(Calendar.MONTH) + 1;
            int d = calendar.get(Calendar.DATE);
            int h = calendar.get(Calendar.HOUR);
            int mins = calendar.get(Calendar.MINUTE);
            int s = calendar.get(Calendar.SECOND);

            StringBuilder takenTimeStringBuilder = new StringBuilder();
            takenTimeStringBuilder.append(y);
            takenTimeStringBuilder.append("-");
            takenTimeStringBuilder.append(m);
            takenTimeStringBuilder.append("-");
            takenTimeStringBuilder.append(d);
            takenTimeStringBuilder.append("-");
            takenTimeStringBuilder.append(h);
            takenTimeStringBuilder.append("-");
            takenTimeStringBuilder.append(mins);
            takenTimeStringBuilder.append("-");
            takenTimeStringBuilder.append(s);
            takenTimeStringBuilder.append("-");

            String takenTime = takenTimeStringBuilder.toString();

            //3. File location to save
            String fileName = testMethodName + "-" + takenTime + ".png";
            String fileLocation = System.getProperty("user.dir") + "/screenshot/" + fileName;

            //4. Capture and attach into report
            File screenshotBase64 = getDriver().getScreenshotAs(OutputType.FILE);
            Path screenshotPath = Paths.get(fileLocation);
            try {
                FileUtils.copyFile(screenshotBase64, new File(fileLocation));
                InputStream inputStream = Files.newInputStream(screenshotPath);
                Allure.addAttachment(testMethodName, inputStream);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
