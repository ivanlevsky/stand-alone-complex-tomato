package com.appium.test;

import com.google.common.io.Files;
import com.java.common.filesystem.AndroidOsUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.AndroidStartScreenRecordingOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class AppiumUtils {
    private static DesiredCapabilities caps = new DesiredCapabilities();

    public static AndroidDriver initDriver(String phoneType, String serverUrl, String initAppName) {
        try {
            caps.setCapability("automationName", "uiautomator2");
            String appPack = "";
            boolean appBackground = false;
            if (phoneType.equals("android")) {
                if (AndroidOsUtils.androidCheckScreenIsLocked()) {
                    throw new Exception("please unlock android screen!!!");
                }
                caps.setCapability("platformVersion", AndroidOsUtils.androidVersion());
                caps.setCapability("platformName", "Android");
                caps.setCapability("uuid", AndroidOsUtils.androidDeviceList());
                if (!initAppName.equals("")) {
                    String result = checkAppStatus(initAppName);
                    if (result.startsWith("true")) {
                        appBackground = true;
                    }
                    appPack = result.replace("true", "");
                }
            }
            caps.setCapability("noReset", "True");
            AndroidDriver androidDriver = new AndroidDriver(new URL(serverUrl), caps);
            if (!appPack.equals("")) {
                if (appBackground) {
                    androidDriver.activateApp(appPack);
                }
            }
            return androidDriver;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String checkAppStatus(String appName) {
        String isAppBackground = "false";
        String[] appPackageAndActivity = AndroidOsUtils.androidSearchPackageByName(appName);
        String appPackage = appPackageAndActivity[0];
        String appActivity = appPackageAndActivity[1];
        String[] openAppPackageAndActivity = AndroidOsUtils.androidCheckAppActive(appPackage);
        String openAppPackage = openAppPackageAndActivity[0];
        String openAppActivity = openAppPackageAndActivity[1];
        String[] backAppPackageAndActivity = AndroidOsUtils.androidCurrentOpenedAppInfo();
        String backAppPackage = backAppPackageAndActivity[0];
        String backAppActivity = backAppPackageAndActivity[1];
        if (!openAppPackage.equals(appPackage)) {
            caps.setCapability("appPackage", appPackage);
            caps.setCapability("appActivity", appActivity);
        } else {
            if (!backAppPackage.equals(openAppPackage) || !backAppActivity.equals(openAppActivity)) {
                isAppBackground = "true";
            }
        }
        return isAppBackground + appPackage;
    }

    public static void closeDriver(AndroidDriver androidDriver) {
        androidDriver.quit();
    }


    public static AndroidElement findElementByClass(AndroidDriver androidDriver, String androidViewClass) {
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 3);
        AndroidElement androidElement = (AndroidElement) androidDriver.findElementByClassName(androidViewClass);
        webDriverWait.until(ExpectedConditions.visibilityOf(androidElement));
        return androidElement;
    }

    public static ArrayList<MobileElement> findElementsByClassName(AndroidDriver androidDriver, String androidViewClass) {
        return (ArrayList<MobileElement>) androidDriver.findElementsByClassName(androidViewClass);
    }

    public static AndroidElement findElementByClassAndText(AndroidDriver androidDriver, String androidViewClass, String text) {
        List<AndroidElement> androidElements = androidDriver.findElementsByClassName(androidViewClass);
        if (androidElements.size() == 1) {
            return androidElements.get(0);
        }
        for (AndroidElement ae : androidElements) {
            if (ae.getAttribute("text") != null) {
                if (ae.getAttribute("text").equals(text)) {
                    return ae;
                }
            }
            if (ae.getAttribute("content-desc") != null) {
                if (ae.getAttribute("content-desc").equals(text)) {
                    return ae;
                }
            }
        }
        return null;
    }

    public static AndroidElement findElementById(AndroidDriver androidDriver, String elementId) {
        WebDriverWait webDriverWait = new WebDriverWait(androidDriver, 3);
        return (AndroidElement) webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id(elementId)));
    }


    public static AndroidElement findElementByAccessibilityId(AndroidDriver androidDriver, String accessibilityId){
        return (AndroidElement) androidDriver.findElementByAccessibilityId(accessibilityId);
    }

    public static AndroidElement findElementByXpath(AndroidDriver androidDriver, String xpath) {
        return (AndroidElement) androidDriver.findElementByXPath(xpath);
    }

    public static void saveScreenshot(AndroidDriver androidDriver, String path) {
        try {
            File file = new File(path);
            if(!file.exists()){
                file.createNewFile();
            }
            Files.copy(androidDriver.getScreenshotAs(OutputType.FILE),file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void startScreenRecord(AndroidDriver androidDriver, boolean bugReport, String recordQuality) {
        AndroidStartScreenRecordingOptions recordOption = new AndroidStartScreenRecordingOptions();
        if(bugReport) {
            recordOption.enableBugReport();
        }
        switch (recordQuality) {
            case "low":
                recordOption.withBitRate(1000000);
                break;
            case "middle":
                recordOption.withBitRate(2000000);
                break;
            case "high":
                recordOption.withBitRate(8000000);
                break;
        }
        androidDriver.startRecordingScreen(recordOption);
    }
    public static void stopScreenRecord(AndroidDriver androidDriver, String path, boolean writeFile) {
        try {

            byte[] record = Base64.getDecoder().decode(androidDriver.stopRecordingScreen());
            if(writeFile){
                File file = new File(path);
                if(!file.exists()){
                    file.createNewFile();
                }
                Files.write(record, file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
