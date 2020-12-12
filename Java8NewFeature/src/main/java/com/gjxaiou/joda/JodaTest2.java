package com.gjxaiou.joda;


import org.joda.time.DateTime;
import org.joda.time.LocalDate;

public class JodaTest2 {

    public static void main(String[] args) {
        // 获取今天时间
        DateTime today = new DateTime();
        // 获取明天时间
        DateTime tomorrow = today.plusDays(1);

        System.out.println(today.toString("yyyy-MM-dd"));
        System.out.println(tomorrow.toString("yyyy-MM-dd"));
        System.out.println("--------");

        // 获取月份第一天
        DateTime d1 = today.withDayOfMonth(1);
        System.out.println(d1.toString("yyyy-MM-dd"));

        System.out.println("--------");

        // 当前日期
        LocalDate localDate = new LocalDate();
        System.out.println(localDate);

        System.out.println("--------");

        // 距离当前日期的后三个月的第一天时间。
        localDate = localDate.plusMonths(3).dayOfMonth().withMinimumValue();
        System.out.println(localDate);
        System.out.println("--------");

        // 计算 2 年前第 3 个月最后 1 天的日期
        DateTime dateTime = new DateTime();

        DateTime dateTime2 = dateTime.minusYears(2).monthOfYear().
                setCopy(3).dayOfMonth().withMinimumValue();

        System.out.println(dateTime2.toString("yyyy-MM-dd"));
    }
}