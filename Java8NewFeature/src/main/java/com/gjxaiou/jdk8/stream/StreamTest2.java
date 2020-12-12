package com.gjxaiou.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class StreamTest2 {

    public static void main(String[] args) {
        IntStream.of(new int[]{5, 6, 7}).forEach(System.out::println);
        System.out.println("-----");

        // 包含前一个，不包含后一个
        IntStream.range(3, 8).forEach(System.out::println);
        System.out.println("-----");

        // 包含前一个，也包含后一个
        IntStream.rangeClosed(3, 8).forEach(System.out::println);


        /**
         * 将 List 中所有元素 * 2 之后相加和
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        // map() 参数是一个 Function，即接收一个值，返回一个值
        System.out.println(list.stream().map(i -> 2 * i).reduce(0, Integer::sum));
    }
}