package com.gjxaiou.jdk8.stream;

import java.util.stream.IntStream;

public class StreamTest8 {

    public static void main(String[] args) {
        // 运行这行，输出 0 1 但是程序一直无法执行完成，因为 iterate() 迭代输出导致 distinct() 一直无法结束
        IntStream.iterate(0, i -> (i + 1) % 2).distinct().limit(6).forEach(System.out::println);
        // 正确的使用方式
        IntStream.iterate(0, i -> (i + 1) % 2).limit(6).distinct().forEach(System.out::println);
    }
}
