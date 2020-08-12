package com.selenium.test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class SeleniumUtils {


    public static void main(String[] args){

    }

    static WebDriver initDriver(String browserType){
        if(browserType.equals("edge")) {
            return new EdgeDriver();
        }
        return null;
    }

    public static void closeDriver(WebDriver driver){
        driver.close();
    }

    static void openBrowserSingleTab(WebDriver driver, String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    static void waitForPageFullLoaded(WebDriver driver, int... maxTimeOut){
        int defaultTimeout = 10;
        if(maxTimeOut.length != 0) {
            defaultTimeout = maxTimeOut[0];
        }
        new WebDriverWait(driver, defaultTimeout).until(
                        (ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete")
        );
    }

    public static void elementAction(WebDriver driver,String xpathValue, String function,String functionParam,int timeOuts){
        WebDriverWait wait=new WebDriverWait(driver, timeOuts);
        WebElement waitElement =  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpathValue)));
        if(function.equals("click")){
            waitElement.click();
        }else if(function.equals("sendKeys")){
            waitElement.sendKeys(functionParam);
        }

    }


    public static WebElement findElementsByXpath(WebDriver driver,String xpath){
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        try {
            if(elements.size() == 0){
                throw new Exception("could not find xpath element !!");
            }else if(elements.size()>1){
                throw new Exception("too many xpath elements !!");
            }

        } catch (Exception e) {
            System.out.println("xpath elements number:"+elements.size());
        }
        return elements.get(0);
    }

    public static void navigateCommand(WebDriver driver, String command, String parameter){
        switch (command) {
            case "to":
                driver.navigate().to(parameter);
                break;
            case "back":
                driver.navigate().back();
                break;
            case "forward":
                driver.navigate().forward();
                break;
            case "refresh":
                driver.navigate().refresh();
                break;
        }

    }
}

