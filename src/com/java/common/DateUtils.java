package com.java.common;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    public static void main(String[] args) {
        String oldDate = "2021-08-25";
		int calcDate = -10;
        System.out.println(CalculateDate(oldDate, calcDate, false));
        System.out.println(CalculateDate(oldDate, calcDate, true));
    }

    public static String CalculateDate(String date, int calcDate, boolean skipWeekends){
		if(skipWeekends){
			int weekEndsCount = 0;
			if(calcDate < 0) {
				for (int i = 0; i < 10; i++){
					if(isDayWeekends(LocalDate.parse(date).minusDays(i).toString())) {
						weekEndsCount -= 1;
					}
				}
			}
			calcDate += weekEndsCount;
		}
        return LocalDate.parse(date).plusDays(calcDate).toString();
    }

    public static Boolean isDayWeekends(String date){
        return LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SATURDAY ||
                LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SUNDAY
                ;
    }
}
