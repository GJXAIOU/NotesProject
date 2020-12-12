package com.gjxaiou.joda;


import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;

import java.util.Date;

public class JodaTest3 {
    /**
     * 给定字符串类型的 UTC 时间转换为标准日期类型
     */
    // 标准 UTC 时间：2014-11-04T09:22:54.876Z   后面 Z 表示没有时区
    public static Date convertUTC2Date(String utcDate) {
        try {
            DateTime dateTime = DateTime.parse(utcDate, DateTimeFormat.forPattern("yyyy-MM-dd'T" +
                    "'HH:mm:ss.SSSZ"));
            return dateTime.toDate();
        } catch (Exception ex) {
            return null;
        }
    }

    // 给定日期类型，转换为 UTC 类型
    public static String convertDate2UTC(Date javaDate) {
        DateTime dateTime = new DateTime(javaDate, DateTimeZone.UTC);
        return dateTime.toString();
    }

    public static String convertDate2LocalByDateFormat(Date javaDate, String dateFormat) {
        DateTime dateTime = new DateTime(javaDate);
        return dateTime.toString(dateFormat);
    }

    public static void main(String[] args) {
        System.out.println(JodaTest3.convertUTC2Date("2014-11-04T09:22:54.876Z"));
        System.out.println(JodaTest3.convertDate2UTC(new Date()));
        System.out.println(JodaTest3.convertDate2LocalByDateFormat(new Date(), "yyyy-MM-dd " +
                "HH:mm:ss"));
    }
}
/**
 * Tue Nov 04 17:22:54 CST 2014  // 转换之后是有时区的
 * 2020-11-01T11:17:15.946Z
 * 2020-11-01 19:17:15
 */