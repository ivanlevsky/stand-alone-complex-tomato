package com.junit.test;

import com.java.common.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;

@Epic("Login Tests Epic")
@Feature("Invalid Login Features")
class StringUtilsTest extends StringUtils {
    private String textToSplit1;
    private String textToSplit2;
    private String[] textGroup1;
    private String[] textGroup2;

    @BeforeEach
    void setUp() {
        textToSplit1 = "abe123*(*#*123山大佛i哦123SOEO";
        textToSplit2 = "124{abiid|DKDL都是}444{123 |**@)}";
        textGroup1 = new String[]{"abe", "*(*#*", "山大佛i哦", "SOEO"};
        textGroup2 = new String[]{"124", "abiid|DKDL都是", "444", "123 |**@)"};
    }

    @Test
    @Story("This is a Fail Story.")
    @Description("This is a Fail Story Description.")
    void testSplitStringByRegex() {
        assertAll("textSplit",
                () -> assertArrayEquals(textGroup1, splitStringByRegex("123",textToSplit1)),
                () -> assertArrayEquals(textGroup2, splitStringByRegex("\\{|\\}",textToSplit2))
        );
    }
}
