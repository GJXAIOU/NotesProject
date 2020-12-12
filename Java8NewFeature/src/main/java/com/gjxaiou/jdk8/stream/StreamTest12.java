package com.gjxaiou.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 需求：第一个集合中每个元素都和第二个集合中每个元素进行拼接。
 * 知识点：flatMap
 */
public class StreamTest12 {

    public static void main(String[] args) {
        List<String> list1 = Arrays.asList("Hi",  "你好");
        List<String> list2 = Arrays.asList("zhangsan", "lisi", "wangwu");

        List<String> result = list1.stream().flatMap(item -> list2.stream().map(item2 -> item + " " + item2)).
                collect(Collectors.toList());
        result.forEach(System.out::println);
    }
}
/** output:
 * Hi zhangsan
 * Hi lisi
 * Hi wangwu
 * 你好 zhangsan
 * 你好 lisi
 * 你好 wangwu
 */
