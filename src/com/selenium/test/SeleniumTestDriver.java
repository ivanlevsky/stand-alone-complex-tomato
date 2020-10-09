package com.selenium.test;

import com.java.common.GlobalParams;
import com.java.common.filesystem.WindowsOsUtils;
import org.openqa.selenium.WebDriver;

public class SeleniumTestDriver {
    public static void main(String[] args) {
        WindowsOsUtils.killProcessByName("IEDriverServer.exe");
        WindowsOsUtils.killProcessByName("chromedriver.exe");
        WindowsOsUtils.killProcessByName("MicrosoftWebDriver.exe");
        WebDriver ieDriver = SeleniumUtils.initDriver("ie", GlobalParams.ieDriverPath);
        WebDriver chromeDriver = SeleniumUtils.initDriver("chrome", GlobalParams.chromeDriverPath);
        WebDriver edgeDriver = SeleniumUtils.initDriver("edge", GlobalParams.edgeDriverPath);
        SeleniumUtils.openBrowserSingleTab(ieDriver, "https://www.baidu.com");
        SeleniumUtils.openBrowserSingleTab(chromeDriver, "https://www.baidu.com");
        SeleniumUtils.openBrowserSingleTab(edgeDriver, "https://www.baidu.com");
    }
}
