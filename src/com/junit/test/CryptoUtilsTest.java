package com.junit.test;

import com.java.common.CryptoUtils;
import com.java.common.filesystem.WindowsOsUtils;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class CryptoUtilsTest extends CryptoUtils {
    private String filePath;

    @BeforeEach
    void setUp() {
         filePath = System.getProperty("user.dir") + "/README.MD";
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
                 () -> assertEquals("84DC54C99868F8BC46F737107A3CCD6D",
                         fileCheckSum(filePath,"md5")),
                 () -> assertEquals("7C37D85DE66F4C83B8E22A5D78583D0DD472F0B7",
                         fileCheckSum(filePath,"sha1")),
                 () -> assertEquals("43CFDEE0E1C3F558A6482C567FB5135788D31BBBF9D28894F0B546512559A86A",
                         fileCheckSum(filePath,"sha256")),
                 () -> assertEquals("F407870F",
                         fileCheckSum(filePath,"crc32"))

         );


    }
}