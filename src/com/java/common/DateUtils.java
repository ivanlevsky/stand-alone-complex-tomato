package com.java.common;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {

    public static String CalculateDate(String date, int calcDate, boolean skipWeekends){
		int passDays = 0;
		if(skipWeekends){
			int weekEndsCount = 0;
			int workDaysCount = 0;
			if(calcDate < 0) {
				while (workDaysCount > calcDate) {
					if	(!isDayWeekends(LocalDate.parse(date).plusDays(passDays).toString())) {
						workDaysCount -= 1;
					}else {
						weekEndsCount -= 1;
					}
					passDays += 1;
				}
			} else if(calcDate > 0){
				while (workDaysCount < calcDate) {
					if	(!isDayWeekends(LocalDate.parse(date).plusDays(passDays).toString())) {
						workDaysCount += 1;
					}else {
						weekEndsCount += 1;
					}
					passDays += 1;
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
