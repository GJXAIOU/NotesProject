package com.gjxaiou.jdk8.lambdaAndFunctionInterface;


import java.util.function.Predicate;

public class PredicateTest {

    public static void main(String[] args) {
        // 输入一个参数，返回一个 boolean 值
        Predicate<String> predicate = p -> p.length() > 5;
        System.out.println(predicate.test("hello1"));
    }
}