package com.selenium.test;

import com.java.common.filesystem.WindowsOsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SeleniumTestBingTranslator {
    public static void main(String[] args) {
        WindowsOsUtils.killProcessByName("MicrosoftWebDriver.exe");
        WebDriver driver = SeleniumUtils.initDriver("edge");
        SeleniumUtils.openBrowserSingleTab(driver, "https://cn.bing.com/translator/");
        SeleniumUtils.waitForPageFullLoaded(driver);
        selectLanguage(driver, "en", "zh-Hans");
        sendTranslateText(driver, "what are you doing");
        SeleniumUtils.closeDriver(driver);
    }

    private static void selectLanguage(WebDriver driver, String fromLang, String toLang){
        WebElement element = driver.findElement(By.id("tta_srcsl"));
        SeleniumUtils.selectElementByValue(element, fromLang);
        element = driver.findElement(By.id("tta_tgtsl"));
        SeleniumUtils.selectElementByValue(element, toLang);
    }

    private static void sendTranslateText(WebDriver driver, String text){
        WebElement element = driver.findElement(By.id("tta_input_ta"));
        element.sendKeys(Keys.LEFT_CONTROL+"a");
        element.sendKeys(Keys.DELETE);
        element.sendKeys(text);
        element = driver.findElement(By.id("tta_output_ta"));
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(element.getText());

    }

}
