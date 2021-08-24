package com.java.common;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class DateUtils {
    public static void main(String[] args) {
        String oldDate = "2021-08-25";
        System.out.println(isDayWeekends(CalculateDate(oldDate, -2)));
    }

    public static String CalculateDate(String date, int calcDate){
        return LocalDate.parse(date).plusDays(calcDate).toString();
    }

    public static Boolean isDayWeekends(String date){
        return LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SATURDAY ||
                LocalDate.parse(date).getDayOfWeek() == DayOfWeek.SUNDAY
                ;
    }
}
