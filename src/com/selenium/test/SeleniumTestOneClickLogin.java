package com.selenium.test;

import com.java.common.filesystem.WindowsOsUtils;
import org.openqa.selenium.WebDriver;

public class SeleniumTestOneClickLogin {
    public static void main(String[] args) {
            WindowsOsUtils.killProcessByName("MicrosoftWebDriver.exe");
            String[] urls = {"tieba.baidu.com","www.baidu.com","cn.bing.com"};
            WebDriver driver = SeleniumUtils.initDriver("edge");
            SeleniumUtils.openBrowserMultiTab(driver,urls);
            SeleniumUtils.switchToTab(driver, urls[1]);
            SeleniumUtils.closeDriver(driver);
    }
}
