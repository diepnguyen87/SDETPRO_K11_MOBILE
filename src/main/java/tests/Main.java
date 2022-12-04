package tests;

import com.google.common.reflect.ClassPath;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import driver.MobileCapabilityTypeEx;
import driver.Platform;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.*;

public class Main implements MobileCapabilityTypeEx {

    @SuppressWarnings("UnstableApiUsage")
    public static void main(String[] args) throws IOException {
        //get all test classes
        final ClassLoader loader = Thread.currentThread().getContextClassLoader();
        List<Class<?>> testClasses = new ArrayList<>();

        for (ClassPath.ClassInfo info : ClassPath.from(loader).getTopLevelClasses()) {
            String classInfoNam = info.getName();
            boolean startWithTestDot = classInfoNam.startsWith("tests.");
            boolean isBaseTestClass = classInfoNam.startsWith("tests.BaseTest");
            boolean isMainTestClass = classInfoNam.startsWith("tests.Main");

            if(startWithTestDot && !isBaseTestClass && !isMainTestClass){
                testClasses.add(info.load());
            }
        }

        //get platform
        String platformName = System.getProperty("platform");
        //String platformName = System.getenv("platform");

        if(platformName == null){
            throw new IllegalArgumentException("[ERROR] Please provide platform name via -Dplatform");
        }

        try{
            Platform.valueOf(platformName);
        }catch (Exception e){
            throw new IllegalArgumentException("[ERROR] We don't support platform: " + platformName + ", supported platforms: " + Arrays.toString(Platform.values()));
        }

        //Devices under test
        List<String> iphoneDeviceList = Arrays.asList("iPhone 8", "iphone XS Max");
        List<String> androidDeviceList = Arrays.asList("MJSKLVNJAUDEPRV8", "PNXGAM88C1002502");
        List<String> deviceList = platformName.equalsIgnoreCase("ios") ? iphoneDeviceList : androidDeviceList;

        //Assign test classes into devices
        final int testNumberEachDevice = testClasses.size() / deviceList.size();
        Map<String, List<Class<?>>> deviceCaps = new HashMap<>();
        for (int deviceIndex = 0; deviceIndex < deviceList.size(); deviceIndex++) {
            int startIndex = deviceIndex * testNumberEachDevice;
            boolean isLastDevice = deviceIndex == deviceList.size() - 1;
            int endIndex = isLastDevice ? testClasses.size() : startIndex + testNumberEachDevice;
            List<Class<?>> subTestList =  testClasses.subList(startIndex, endIndex);
            deviceCaps.put(deviceList.get(deviceIndex), subTestList);
        }

        //Build dynamic test suit
        TestNG testNG = new TestNG();
        XmlSuite suite = new XmlSuite();
        suite.setName("Regression");

        List<XmlTest> allTest = new ArrayList<>();
        for (String deviceName : deviceCaps.keySet()) {
            XmlTest test = new XmlTest(suite);
            test.setName(deviceName);
            List<XmlClass> xmlClasses = new ArrayList<>();
            List<Class<?>> dedicatedClasses = deviceCaps.get(deviceName);
            for (Class<?> dedicatedClass : dedicatedClasses) {
                xmlClasses.add(new XmlClass(dedicatedClass.getName()));
            }
            test.setXmlClasses(xmlClasses);
            test.addParameter(UDID, deviceName);
            test.addParameter(PLATFORM_NAME, platformName);
            test.addParameter(PLATFORM_VERSION, "15.0");
            test.addParameter(SYSTEM_PORT, String.valueOf(new SecureRandom().nextInt(1000) + 8300));
            allTest.add(test);
        }
        suite.setTests(allTest);
        suite.setParallel(XmlSuite.ParallelMode.TESTS);
        suite.setThreadCount(10);
        System.out.println(suite.toXml());

        //Add test suite into suite list
        List<XmlSuite> suites = new ArrayList<>();
        suites.add(suite);

        //Invoke Run Method
        testNG.setXmlSuites(suites);
        testNG.run();
    }
}
