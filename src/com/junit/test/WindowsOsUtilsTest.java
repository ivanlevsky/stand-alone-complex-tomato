package com.junit.test;

import com.java.common.filesystem.WindowsOsUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WindowsOsUtilsTest extends WindowsOsUtils {
    private String processIsRunning;
    private String processNotRunning;
    private String processToTestRunAndKill;
    private String command;

    @BeforeEach
    void setUp() {
        processIsRunning = "java.exe";
        processNotRunning = "retroarch.exe";
        processToTestRunAndKill = "notepad.exe";
        command = "echo 123";
    }

    @AfterEach
    void tearDown() {
        if(WindowsOsUtils.isWindowsOS()) {
            killProcessByName(processToTestRunAndKill);
        }
    }

    @Test
    void testKillAndRunProcessByName() {
        if(WindowsOsUtils.isWindowsOS()) {
            runProcessByName(processToTestRunAndKill);
            assertTrue(checkProcessRunning(processToTestRunAndKill));
            killProcessByName(processToTestRunAndKill);
            assertFalse(checkProcessRunning(processToTestRunAndKill));
        }
    }



    @Test
    void testCheckProcessRunning() {
        if(WindowsOsUtils.isWindowsOS()) {
            assertAll("check process running",
                    () -> assertTrue(checkProcessRunning(processIsRunning)),
                    () -> assertFalse(checkProcessRunning(processNotRunning))
            );
        }
    }

    @Test
    void testGetCommandOutput() {
        if(WindowsOsUtils.isWindowsOS()) {
            assertEquals("123", getCommandOutput(command).trim());
        }
    }

    @Test
    void testGetShellOutput() {
        if(WindowsOsUtils.isWindowsOS()) {
            assertEquals("123", getShellOutput("powershell", command).trim());
        }
    }
}