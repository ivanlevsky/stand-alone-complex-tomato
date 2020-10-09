package com.selenium.test;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SeleniumUtils {


    public static void main(String[] args){

    }

    static WebDriver initDriver(String browserType, String... driverPath){
        if(browserType.equals("edge")) {
            return new EdgeDriver();
        }
        if(browserType.equals("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("credentials_enable_service", false);
            prefs.put("profile.password_manager_enabled", false);
            chromeOptions.setExperimentalOption("prefs", prefs);
            if(driverPath.length != 0){
                System.setProperty("webdriver.chrome.driver", driverPath[0]);
            }
            return new ChromeDriver(chromeOptions);
        }
        return null;
    }

    public static void closeDriver(WebDriver driver){
        driver.close();
    }

    public static void openBrowserSingleTab(WebDriver driver, String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public static void openBrowserMultiTab(WebDriver driver, String[] urls, int... maxTimeOut){
        for (int i = 0; i < urls.length; i++) {
            driver.get(urls[i]);
            new WebDriverWait(driver, 10).until(
                    (ExpectedCondition<Boolean>) wd ->
                            ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
            );
            if(i < urls.length - 1){
                ((JavascriptExecutor)driver).executeScript("window.open()");
                if(driver.getWindowHandles().iterator().hasNext()) {
                    driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(i + 1));
                }
            }
        }
    }

    public static void switchToTab(WebDriver driver, String targetTabUrl){
        for (int i = 0; i < driver.getWindowHandles().size(); i++) {
            driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(i));
            if(driver.getCurrentUrl().contains(targetTabUrl)){
                break;
            }
        }
    }

    public static void selectElementByValue(WebElement element, String selectValue){
        Select select = new Select(element);
        select.selectByValue(selectValue);
    }

    public static void saveScreenShot(WebDriver driver, String imagePath, String imageName){
        try {
            File file = new File(imagePath + imageName);
            if(!file.exists()){
                file.createNewFile();
            }
            Files.copy(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE),file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void waitForPageFullLoaded(WebDriver driver, int... maxTimeOut){
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                        (ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public static void waitForElementDisappeard(WebDriver driver, WebElement element, int... maxTimeOut){
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                ExpectedConditions.invisibilityOf(element)
        );
    }

    public static void waitForElementAppeard(WebDriver driver, WebElement element, int... maxTimeOut){
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                ExpectedConditions.visibilityOf(element)
        );
    }


    public static void waitForElementToBeClickable(WebDriver driver, String elementXpath, int... maxTimeOut){
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                ExpectedConditions.elementToBeClickable(By.xpath(elementXpath))
        ).click();
    }

    public static void waitForFrameAndSwitchToFrame(WebDriver driver, String frameName, int... maxTimeOut) {
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName)
        );
    }

    public static void waitForElementExist(WebDriver driver, String elementXpath, int... maxTimeOut) {
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath))
        );
    }

    public static WebElement findElementByXpath(WebDriver driver, String xpath){
        return driver.findElement(By.xpath(xpath));
    }

    public static WebElement findElementByName(WebDriver driver, String name){
        return driver.findElement(By.name(name));
    }

    public static WebElement findElementById(WebDriver driver, String id){
        return driver.findElement(By.id(id));
    }

    public static WebElement findElementByClassName(WebDriver driver, String className){
        return driver.findElement(By.className(className));
    }


//    public static WebElement findElementsByXpath(WebDriver driver,String xpath){
//        List<WebElement> elements = driver.findElements(By.xpath(xpath));
//        try {
//            if(elements.size() == 0){
//                throw new Exception("could not find xpath element !!");
//            }else if(elements.size()>1){
//                throw new Exception("too many xpath elements !!");
//            }
//
//        } catch (Exception e) {
//            System.out.println("xpath elements number:"+elements.size());
//        }
//        return elements.get(0);
//    }
//
//    public static void navigateCommand(WebDriver driver, String command, String parameter){
//        switch (command) {
//            case "to":
//                driver.navigate().to(parameter);
//                break;
//            case "back":
//                driver.navigate().back();
//                break;
//            case "forward":
//                driver.navigate().forward();
//                break;
//            case "refresh":
//                driver.navigate().refresh();
//                break;
//        }
//
//    }
}

