package com.gjxaiou.jdk8.stream;


import java.util.Arrays;
import java.util.List;

/**
 * 知识点：中间操作和终止操作
 * 每个元素首字母大写然后输出
 */
public class StreamTest7 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        list.stream().map(item -> item.substring(0, 1).toUpperCase() + item.substring(1)).
                forEach(System.out::println);


        // 这个是不会执行的，因为 map() 是一个中间操作，在没有终止操作的情况下是不会执行的。因为是惰性求值
        list.stream().map(item -> {
            String result = item.substring(0, 1).toUpperCase() + item.substring(1);
            System.out.println("test");
            return result;
        });

        // 这个会执行
        list.stream().map(item -> {
            String result = item.substring(0, 1).toUpperCase() + item.substring(1);
            System.out.println("test");
            return result;
        }).forEach(System.out::println);
    }
}
