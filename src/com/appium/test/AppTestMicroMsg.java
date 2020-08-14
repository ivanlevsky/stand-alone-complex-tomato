package com.appium.test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class AppTestMicroMsg {
    private static AndroidDriver appDriver;
    private static String[] bottomClickElements = {"微信", "通讯录", "发现", "我"};
    public static void main(String[] args) throws InterruptedException {

        appDriver = AppiumUtils.initDriver("android", "http://localhost:4723/wd/hub", "微信");
        Thread.sleep(5000L);
        microMsgScan();
    }

    public static void enterMicroMsgDialog(String selectMsgTitle) {
        AndroidElement el = AppiumUtils.findElementByClassAndText(appDriver, "android.widget.TextView", bottomClickElements[0]);
        el.click();
        AppiumUtils.findElementByClassAndText(appDriver, "android.view.View", selectMsgTitle).click();
    }

    public static void sendMicroMsgMessages(String text) {
        AndroidElement edit = AppiumUtils.findElementByClass(appDriver, "android.widget.EditText");
        edit.click();
        edit.setValue(text);
        AppiumUtils.findElementByClass(appDriver, "android.widget.Button").click();
    }

    public static void quitMicroMsgCurrentPage() {
        AppiumUtils.findElementByXpath(appDriver, "//android.widget.ImageView[@content-desc='返回']").click();
    }

    public static void enterFriendsSpace() {
        AndroidElement el = AppiumUtils.findElementByClassAndText(appDriver,
                "android.widget.TextView", bottomClickElements[2]);
        el.click();
        AppiumUtils.findElementByClassAndText(appDriver, "android.widget.TextView", "朋友圈").click();
    }

    public static void microMsgScan() throws InterruptedException {
        AndroidElement el = AppiumUtils.findElementByClassAndText(appDriver,
                "android.widget.RelativeLayout", "更多功能按钮");
        el.click();
        Thread.sleep(2000L);
        el = AppiumUtils.findElementByClassAndText(appDriver, "android.widget.TextView", "扫一扫");
        el.click();
    }
}
