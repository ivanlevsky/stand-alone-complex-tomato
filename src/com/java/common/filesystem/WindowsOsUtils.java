package com.java.common.filesystem;

import java.io.*;
import java.util.Locale;

public class WindowsOsUtils {
    public static void main(String[] args) {
        System.out.println(getShellOutput("adb shell", "dumpsys window | grep mDreamingLockscreen"));
//        System.out.println(getCommandOutput("dir"));
    }

    public static void killProcessByName(String processName){
        try {
            Runtime.getRuntime().exec("taskkill /f /im  "+processName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getCommandOutput(String cmd){
        StringBuilder output = new StringBuilder();
        String charset = Locale.getDefault().toString().equals("zh_CN")?"GBK":"UTF-8";
        try {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd","/c", cmd);
            Process process = processBuilder.start();
            String line;
            if(process.waitFor() == 0){
                BufferedReader reader=new BufferedReader(new InputStreamReader(
                        process.getInputStream(), charset));
                while((line = reader.readLine()) != null) {
                    output.append(line).append(System.lineSeparator());
                }
                reader.close();
            }
            process.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return output.toString();
    }


    public static String getShellOutput(String shellCmd, String executeCmd){
        StringBuilder output = new StringBuilder();
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd","/c", shellCmd);

        try {
            String line;
            Process process = processBuilder.start();
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                    process.getOutputStream()));
            out.write(executeCmd);
            out.flush();
            out.close();
            while ((line = in.readLine()) != null) {
                output.append(line).append(System.lineSeparator());
            }
            in.close();
            process.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}
