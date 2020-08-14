package com.appium.test;

import com.java.common.GlobalParams;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.util.ArrayList;
import java.util.List;

public class AppTest12306Ticket {
    private static AndroidDriver appDriver;
    private static String[] bottomClickElements = {"首页", "出行服务", "订单", "铁路会员", "我的"};
    private static String[] orderPageElements = {"未完成", "已支付", "候补订单", "本人车票"};
    private static String[] selectStationsListTag = {"最近常用", "热门车站", "A", "B"};

    public static void main(String[] args) throws InterruptedException {
        appDriver = AppiumUtils.initDriver("android", "http://localhost:4723/wd/hub", "铁路12306");
        Thread.sleep(6000L);
        AppiumUtils.startScreenRecord(appDriver, true, "low");
        Thread.sleep(3000L);
        AppiumUtils.stopScreenRecord(appDriver, GlobalParams.appiumScreenrecordPath, true);
        appDriver.quit();
    }

    public static void checkTicketOrder(String orderPageElement, String... orderPageOption) {
        AndroidElement el = AppiumUtils.findElementByClassAndText(appDriver,
                "android.widget.RadioButton",
                bottomClickElements[2]);
        el.click();
        AppiumUtils.findElementByClassAndText(appDriver, "android.view.View", orderPageElement).click();
        if (orderPageElement.equals("已支付")) {
            AppiumUtils.findElementByClassAndText(appDriver, "android.view.View", orderPageOption[0]).click();
        }
    }

    public static void selectStationListByTag(String selectTag, String location) {
        String xpath = "//android.view.View[@content-desc=\"" + selectTag + "\"]/..";
        AndroidElement el = AppiumUtils.findElementByXpath(appDriver, xpath);
        AndroidElement nel = (AndroidElement) el.findElementByClassName("android.widget.ListView");
        List<MobileElement> els = nel.findElementsByClassName("android.view.View");
        for (MobileElement e : els) {
            if (e.getAttribute("content-desc").equals(location)) {
                e.click();
                break;
            }
        }
    }

    public static void setTrainLocation(String departOrArrive, String trainLocation) throws InterruptedException {
        String tldXpath = "";
        if(departOrArrive.equals("depart")){
            tldXpath = "home_page_train_dep1";
        }else if(departOrArrive.equals("arrive")) {
            tldXpath = "home_page_train_arr1";
        }
        AndroidElement el = AppiumUtils.findElementById(appDriver, "com.MobileTicket.launcher:id/" + tldXpath);
        if(!el.getText().equals(trainLocation)){
            el.click();
            Thread.sleep(2000L);
            el = AppiumUtils.findElementByClassAndText(appDriver, "android.widget.EditText", "请输入城市/车站名");
            el.click();
            el.sendKeys(trainLocation);
            ArrayList<MobileElement> els = AppiumUtils.findElementsByClassName(appDriver, "android.widget.ListView");
            MobileElement nel = els.get(els.size()-1);
            for(MobileElement me : nel.findElementsByClassName("android.view.View")){
                if(me.getAttribute("text").equals(trainLocation)){
                    me.click();
                    break;
                }
                if(me.getAttribute("content-desc").equals(trainLocation)){
                    me.click();
                    break;
                }
            }

        }
    }

    public static void quitStationSelectPage() {
        AppiumUtils.findElementByAccessibilityId(appDriver, "返回").click();
    }
}
