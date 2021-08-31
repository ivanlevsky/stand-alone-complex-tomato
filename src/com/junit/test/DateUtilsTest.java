package com.junit.test;

import com.java.common.DateUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class DateUtilsTest extends DateUtils{

	private String oldDate;
    private int calcDate1;
    private int calcDate2;


    @BeforeEach
    void setUp() {
    	oldDate = "2021-08-25";
    	calcDate1 = 10;
    	calcDate2 = -10;
    }

    @Test
    void testCalculateDate() {
        assertAll("CalculateDate",
                () -> assertEquals("2021-09-04", CalculateDate(oldDate, calcDate1, false)),
                () -> assertEquals("2021-09-08", CalculateDate(oldDate, calcDate1, true)),
                () -> assertEquals("2021-08-15", CalculateDate(oldDate, calcDate2, false)),
                () -> assertEquals("2021-08-11", CalculateDate(oldDate, calcDate2, true))
        );
    }

}
