package com.time.tracking.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTimeUtils {

    public static Date convertStringToDate(String date, String timeFormat) {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(timeFormat);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Date receiveBeginOfDay(Date date) {
        return receiveMomentOfDay(date, 0, 0, 0, 0);
    }

    public static Date receiveEndOfDay(Date date) {
        return receiveMomentOfDay(date, 23, 59, 59, 999);
    }

    private static Date receiveMomentOfDay(Date date, int hour, int minute, int second, int millisecond) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, second);
        cal.set(Calendar.MILLISECOND, millisecond);
        return cal.getTime();
    }

    public static Date addHourToDate(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, hour);
        return cal.getTime();
    }

    public static String changeDataToStringFormat(Date currentDate, String timeFormat) {
        SimpleDateFormat dt = new SimpleDateFormat(timeFormat);
        return dt.format(currentDate);
    }

    public static String changeStingDataToStingFormat(String date, String timeFormatFromSting, String timeFormatToString) {
        Date currentDate = convertStringToDate(date, timeFormatFromSting);
        return changeDataToStringFormat(currentDate, timeFormatToString);
    }

    public static int differenceBtwTwoDatesInDays(Date firstDate, Date secondDate) {
        long milliseconds = secondDate.getTime() - firstDate.getTime();
        return (int) (milliseconds / (24 * 60 * 60 * 1000));
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
