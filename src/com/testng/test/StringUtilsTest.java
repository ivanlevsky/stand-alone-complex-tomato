package com.testng.test;

import com.java.common.StringUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.*;

public class StringUtilsTest extends StringUtils {
    private String textToSplit1;
    private String textToSplit2;
    private String[] textGroup1;
    private String[] textGroup2;

    @BeforeMethod
    public void setUp() {
        textToSplit1 = "abe123*(*#*123山大佛i哦123SOEO";
        textToSplit2 = "124{abiid|DKDL都是}444{123 |**@)}";
        textGroup1 = new String[]{"abe", "*(*#*", "山大佛i哦", "SOEO"};
        textGroup2 = new String[]{"124", "abiid|DKDL都是", "444", "123 |**@)"};
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void testTestSplitStringByRegex() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(textGroup1, splitStringByRegex("123",textToSplit1));
        softAssert.assertEquals(textGroup2, splitStringByRegex("\\{|\\}",textToSplit2));
        softAssert.assertAll("textSplit");

    }
}