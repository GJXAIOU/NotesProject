package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Arrays;
import java.util.List;

public class MethodReferenceDemo {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
        // Lambda 表达式
        list.forEach(item -> System.out.println(item));
        System.out.println("-----------");
        // method reference
        list.forEach(System.out::println);
        System.out.println("-----------");
        // 字符串拼接输出，结尾不回车
        list.forEach(System.out::format);
        System.out.println("-----------");
    }
}
/** output:
 * hello
 * world
 * -----------
 * hello
 * world
 * -----------
 * helloworld-----------
 */

