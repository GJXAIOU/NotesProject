package com.gjxaiou.jdk8.streamSourceCode;


import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyComparatorTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("nihao", "hello", "world", "welcome");

        //
        Collections.sort(list, (item1, item2) -> item1.length() - item2.length());
        Collections.sort(list, (item1, item2) -> item2.length() - item1.length());

        // 按照字符串长度降序排列，使用方法引用
        Collections.sort(list, Comparator.comparingInt(String::length).reversed());

        // 使用 Lambda 表达式实现上面同样功能，同时这里参数类型是无法推断出来的，需要显式指定。如果不写的情况下，默认是 Object，具体看源码。
        Collections.sort(list, Comparator.comparingInt((String item) -> item.length()).reversed());

        /**
         * 方式二：
         */
        list.sort(Comparator.comparingInt(String::length).reversed());
        list.sort(Comparator.comparingInt((String item) -> item.length()).reversed());

        // 双重比较，thenComparing 只有在前面相同才会执行，具体看源码。
        Collections.sort(list,
                Comparator.comparingInt(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));

        // 相当于实现了后面的 String.CASE_INSENSITIVE_ORDER 方法
        Collections.sort(list, Comparator.comparingInt(String::length).
                thenComparing((item1, item2) -> item1.toLowerCase().compareTo(item2.toLowerCase())));

        // 使用方法引用实现
        Collections.sort(list, Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase)));

        Collections.sort(list, Comparator.comparingInt(String::length).
                thenComparing(Comparator.comparing(String::toLowerCase,
                        Comparator.reverseOrder())));

        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase,
                        Comparator.reverseOrder())));

        Collections.sort(list, Comparator.comparingInt(String::length).reversed().
                thenComparing(Comparator.comparing(String::toLowerCase, Comparator.reverseOrder())).
                thenComparing(Comparator.reverseOrder()));

        System.out.println(list);
    }
}
