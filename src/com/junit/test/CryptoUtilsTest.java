package com.junit.test;

import com.java.common.CryptoUtils;
import com.java.common.filesystem.WindowsOsUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilsTest extends CryptoUtils {
    private String filePath;

    @BeforeEach
    void setUp() {
         filePath = System.getProperty("user.dir") + "/LICENSE";
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Disabled //this method not complete
    void testEncode() {

    }

    @Test
    @Deprecated // md5(), sha1() deprecate
    void testFileCheckSum() {
         System.out.println("test md5, sha1, sha256, crc32 checksum");
         System.out.println("filePath : "+ filePath);
         System.out.println(WindowsOsUtils.isWindowsOS());
         assertAll("checkSum",
                 () -> assertEquals("B42A17A0F83B737FAF403CAA6B2641AF",
                         fileCheckSum(filePath,"md5")),
                 () -> assertEquals("29D1871D6CD6CBDA3D35AB3A1448E58822FA1D55",
                         fileCheckSum(filePath,"sha1")),
                 () -> assertEquals("9F2C16E91B305CC547E159DEDC4E34BDE312B120CB2749E418739C01774156FE",
                         fileCheckSum(filePath,"sha256")),
                 () -> assertEquals("7A450B78",
                         fileCheckSum(filePath,"crc32"))

         );


    }
}