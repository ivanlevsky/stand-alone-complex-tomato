package com.java.common;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.Duration;
import java.time.LocalTime;

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

    public static String getLastDayOfMonth(String date) {
    	return LocalDate.parse(date).with(TemporalAdjusters.lastDayOfMonth()).toString();
    }

    public static String getFirstDayOfMonth(String date) {
    	return LocalDate.parse(date).with(TemporalAdjusters.firstDayOfMonth()).toString();
    }

	private static String betweenHours(String startTime, String endTime) {
		Duration duration = Duration.between(LocalTime.parse(startTime),
				LocalTime.parse(endTime));
		return String.valueOf((float) (duration.toHoursPart() +
				(duration.toMinutesPart() + duration.toSecondsPart()/60.0)/60.0));
	}
	
	private static String getLastDayOfMonth(String date) {
		return LocalDate.from(
				LocalDate.parse(date)
				).with(TemporalAdjusters.lastDayOfMonth()).toString();
	}
		
	private static String getLastDayOfLastMonth(String date) {
		return LocalDate.from(
				LocalDate.parse(date)
				).with(TemporalAdjusters.firstDayOfMonth()).minusDays(1).toString();
	}
}
