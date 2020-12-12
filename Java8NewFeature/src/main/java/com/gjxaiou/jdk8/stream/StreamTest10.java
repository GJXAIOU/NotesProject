package com.gjxaiou.jdk8.stream;


import java.util.Arrays;
import java.util.List;

public class StreamTest10 {

    public static void main(String[] args) {
        // 将列表中长度为 5 的第一个单词打印
        List<String> list = Arrays.asList("hello", "world", "hello world");

        list.stream().mapToInt(item -> item.length()).filter(length -> length == 5).
                findFirst().ifPresent(System.out::println);

        // 会对流中每个元素依次应用第一个操作、第二个操作、。。。。并且存在短路运算（前面为假则后面就不执行了）
        list.stream().mapToInt(item -> {
            int length = item.length();
            System.out.println(item);
            return length;
        }).filter(length -> length == 5).findFirst().ifPresent(System.out::println);

    }
}
