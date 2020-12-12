package com.gjxaiou.jdk8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest5 {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "helloworld", "test");
        // 将集合中每个元素转换为大写然后输出
        list.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------");

        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        list2.stream().map(item -> item * item).collect(Collectors.toList()).forEach(System.out::println);

        System.out.println("----------");

        // flatMap：平整化的 Map
        Stream<List<Integer>> stream = Stream.of(Arrays.asList(1),
                Arrays.asList(2, 3), Arrays.asList(4, 5, 6));

        // theList -> theList.stream() 将每个 List 都转换为 Stream，然后通过 flatMap 将所有 Stream 打平
        stream.flatMap(theList -> theList.stream()).map(item -> item * item).forEach(System.out::println);
    }
}
