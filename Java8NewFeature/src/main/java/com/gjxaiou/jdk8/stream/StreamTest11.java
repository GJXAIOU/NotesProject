package com.gjxaiou.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 知识点：map 和 flatMap 区别
 */
public class StreamTest11 {

    public static void main(String[] args) {
        /**
         * 需求：对 List 中所有元素按照空格划分之后去重输出
         */
        List<String> list = Arrays.asList("hello welcome", "world hello",
                "hello world hello", "hello welcome");

        // 错误用法：因为 map() 返回一个 String[]，四个元素划分为四个 String[]，并且四个肯定不相同，所以最终相当于输出了所有元素。
        List<String[]> result = list.stream().map(item -> item.split(" ")).distinct().
                collect(Collectors.toList());
        result.forEach(item -> Arrays.asList(item).forEach(System.out::println));

        List<String> result2 =
                list.stream().map(item -> item.split(" ")).flatMap(Arrays::stream).distinct().
                        collect(Collectors.toList());

        result2.forEach(System.out::println);
    }
}
