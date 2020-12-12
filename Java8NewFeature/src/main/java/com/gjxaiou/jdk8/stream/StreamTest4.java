package com.gjxaiou.jdk8.stream;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest4 {

    public static void main(String[] args) {
        Stream<String> stream = Stream.of("hello", "world", "helloWorld");

        // 将流转换为字符串数组
        String[] stringArray = stream.toArray(length -> new String[length]);
        // 使用方法引用改造上面的 Lambda 表达式写法
        // String[] stringArray = stream.toArray(String[]::new);
        Arrays.asList(stringArray).forEach(System.out::println);


        /**
         * 将流转换为一个 List
         */
        Stream<String> stream2 = Stream.of("hello", "world", "helloWorld");
        // collect() 是一个终止方法
        List<String> list = stream2.collect(Collectors.toList());
        // 上面一行等价于下面这个，本质是 Collectors.toList() 就是对下面代码的封装
        List<String> list1 = stream2.collect(() -> new ArrayList(), // 不接受参数，返回一个结果，也是最终结果
                (theList, item) -> theList.add(item),// 两个参数，进行遍历然后加入集合
                (theList1, theList2) -> theList1.addAll(theList2));// 将每次遍历形成的 List2 加入最终返回的 List2 中
        // 使用方法引用简化上面代码
        List<String> list2 = stream2.collect(LinkedList::new, LinkedList::add, LinkedList::addAll);

        list.forEach(System.out::println);

        // 流转换为集合的方式二
        Stream<String> stream3 = Stream.of("hello", "world", "helloWorld");
        // 可以自定义返回 List 的类型
        List<String> list3 = stream3.collect(Collectors.toCollection(ArrayList::new));
        list3.forEach(System.out::println);

        // 自定义转换为 Set 类型
        Stream<String> stream4 = Stream.of("hello", "world", "helloWorld");
        Set<String> set = stream4.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set.getClass());

        set.forEach(System.out::println);

        // 转换为 String 对象
        Stream<String> stream5 = Stream.of("hello", "world", "helloWorld");
        String str = stream5.collect(Collectors.joining()).toString();
        System.out.println(str);
    }
}