package com.testng.test;

import com.java.common.filesystem.WindowsOsUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class WindowsOsUtilsTest extends WindowsOsUtils {
    private String processIsRunning;
    private String processNotRunning;
    private String processToTestRunAndKill;
    private String command;

    @BeforeMethod
    public void setUp() {
        processIsRunning = "java.exe";
        processNotRunning = "retroarch.exe";
        processToTestRunAndKill = "notepad.exe";
        command = "echo 123";
    }

    @AfterMethod
    public void tearDown() {
    }


    @Test
    public void testKillAndRunProcessByName() {
        runProcessByName(processToTestRunAndKill);
        assertTrue(checkProcessRunning(processToTestRunAndKill));
        killProcessByName(processToTestRunAndKill);
        assertFalse(checkProcessRunning(processToTestRunAndKill));
    }

    @Test
    public void testCheckProcessRunning() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(checkProcessRunning(processIsRunning));
        softAssert.assertFalse(checkProcessRunning(processNotRunning));
        softAssert.assertAll("check process running");
    }

    @Test
    public void testGetCommandOutput() {
        assertEquals("123",getCommandOutput(command).trim());
    }

    @Test
    public void testGetShellOutput() {
        assertEquals("123", getShellOutput("powershell",command).trim());
    }
}