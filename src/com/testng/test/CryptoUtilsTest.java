package com.testng.test;

import com.java.common.CryptoUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class CryptoUtilsTest extends CryptoUtils {
    private String filePath;

    @BeforeMethod
    public void setUp() {
        filePath = System.getProperty("user.dir") + "\\LICENSE";
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    @Ignore
    public void testEncode() {
        System.out.println("skip this test");
    }

    @Test
    @Deprecated
    public void testFileCheckSum() {
        System.out.println("test md5, sha1, sha256, crc32 checksum");
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("B42A17A0F83B737FAF403CAA6B2641AF",
                fileCheckSum(filePath,"md5"));
        softAssert.assertEquals("29D1871D6CD6CBDA3D35AB3A1448E58822FA1D55",
                        fileCheckSum(filePath,"sha1"));
        softAssert.assertEquals("9F2C16E91B305CC547E159DEDC4E34BDE312B120CB2749E418739C01774156FE",
                        fileCheckSum(filePath,"sha256"));
        softAssert.assertEquals("7A450B78", fileCheckSum(filePath,"crc32"));
        softAssert.assertAll("checkSum");
    }
}