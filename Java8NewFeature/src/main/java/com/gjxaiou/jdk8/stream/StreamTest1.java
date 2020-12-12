package com.gjxaiou.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Steam 流的三种创建方式
 */
public class StreamTest1 {

    public static void main(String[] args) {
        // 方式一：
        Stream stream1 = Stream.of("hello", "world", "hello world");

        // 方式二：
        String[] myArray = new String[]{"hello", "world", "hello world"};
        Stream stream2 = Stream.of(myArray);
        Stream stream3 = Arrays.stream(myArray);

        // 方式三：
        List<String> list = Arrays.asList(myArray);
        Stream stream4 = list.stream();
    }
}