package com.java.common.filesystem;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.java.common.GlobalParams;
import com.java.common.StringUtils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class AndroidOsUtils {
    public static void main(String[] args) {
//        androidAaptGetAppInfo("/data/app/MiuiScreenRecorder/MiuiScreenRecorder.apk");
    }

    public static String androidDeviceList(){
        return WindowsOsUtils.getCommandOutput("adb devices").split("\n")[1].replace("device", "").trim();
    }

    public static String androidVersion(){
        return WindowsOsUtils.getCommandOutput("adb shell getprop ro.build.version.release");
    }

    public static String androidApiVersion() {
        return WindowsOsUtils.getCommandOutput("adb shell getprop ro.build.version.sdk");
    }

    public static String[] androidCurrentOpenedAppInfo() {
        String msg = WindowsOsUtils.getShellOutput("adb shell",
                "dumpsys window windows | grep -E \'mCurrentFocus|mFocusedApp\'");
        msg = StringUtils.splitStringByRegex("\\{|\\}",msg)[1].split(" ")[2];
        return new String[]{msg.split("/")[0], msg.split("/")[1]};
    }

    public static String[] androidCheckAppActive(String appPackage) {
        String[] msg = WindowsOsUtils.getShellOutput("adb shell",
                "dumpsys window windows | grep -e 'Window #'").split("\r\n");
        for(String m: msg){
            if(m.contains(appPackage)){
                msg = StringUtils.splitStringByRegex("\\{|\\}", m.trim())[1].split(" ")[2].split("/");
                return new String[]{msg[0],msg[1]};
            }
        }
        return new String[]{"not_find","not_find"};
    }

    public static void androidAaptInstall(){
        System.out.println(WindowsOsUtils.getCommandOutput("adb","push", GlobalParams.aaptPath, "/data/local/tmp"));
        System.out.println(WindowsOsUtils.getCommandOutput("adb shell chmod 0755 /data/local/tmp/aapt-arm-pie"));
    }

    public static void androidHomeButton(){
        WindowsOsUtils.getShellOutput("adb shell",
                "am start -W -c android.intent.category.HOME -a android.intent.action.MAIN");
    }

    public static String  androidAllPackageList() {
        String[] msg = StringUtils.splitStringByRegex("\r\n",
                WindowsOsUtils.getShellOutput("adb shell","pm list packages -3 -f"));

        StringBuilder packageList = new StringBuilder();
        String pkg, name, launch;
        ArrayList<String> msList = new ArrayList<>();
        for(String ms : msg){
            msList.clear();
            for(String s:androidAaptGetAppInfo(ms.substring(ms.indexOf("/"),ms.lastIndexOf("="))).replace("\r\n","").split("'")){
                if(!s.equals("")) {
                    msList.add(s);
                }
            }

            if(msList.contains("package: name=")){
                pkg = msList.get(msList.indexOf("package: name=")+1);
            }else {
                pkg = "";
            }
            if(msList.contains("application-label-zh-CN:")){
                name = msList.get(msList.indexOf("application-label-zh-CN:")+1);
            }else if(msList.contains("application-label:")){
                name = msList.get(msList.indexOf("application-label:")+1);
            }else if(msList.contains("application: label=")){
                name = msList.get(msList.indexOf("application: label=")+1);
            }else{
                name = "no label name";
            }
            if(msList.contains("launchable-activity: name=")){
                launch = msList.get(msList.indexOf("launchable-activity: name=")+1);
            }else {
                launch = androidSearchAppActivity(pkg);
            }

            packageList.append("'").append(pkg).append(",")
                    .append(name).append(",").append(launch).append("',");
        }

        System.out.println("["+packageList+"]");
//       write_string_to_file(android_apk_list, package_list, 'utf8')
        return packageList.toString();
    }

    public static String androidAaptGetAppInfo(String appPath){
        String cmd = "/data/local/tmp/aapt-arm-pie d badging " + appPath + " |grep -E " +
                "'package|launchable-activity|application-label'";
        return WindowsOsUtils.getShellOutput("adb shell",cmd);
    }

    public static String androidSearchAppActivity(String appPackage) {
        String cmd = "cmd package resolve-activity " + appPackage + " |grep name";
        String msg = WindowsOsUtils.getShellOutput("adb shell", cmd);
        if(msg.equals("")){
            msg = "no activity info";
        }else{
            msg = msg.replace("\r\n", "").split("name=")[1].trim();
        }
        return msg;
    }

    public static String[] androidSearchPackageByName(String appName){
        try {
            String[] readList = Files.asCharSource(new File(GlobalParams.androidApklist),Charsets.UTF_8).read()
                    .replace("[","").replace("]","").replace("'","").split(",");
            for (int i = 0; i < readList.length; i++) {
                if(readList[i].equals(appName)) {
                    return new String[]{readList[i-1],readList[i+1]};
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }

    public static boolean androidCheckScreenIsLocked(){
        String cmd = "dumpsys window | grep mDreamingLockscreen";
        String msg = WindowsOsUtils.getShellOutput("adb shell", cmd);
        return msg.contains("mDreamingLockscreen=true");
    }

}
