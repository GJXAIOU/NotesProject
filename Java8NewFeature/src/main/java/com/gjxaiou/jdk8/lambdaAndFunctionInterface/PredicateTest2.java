package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class PredicateTest2 {

    public static void main(String[] args) {

        // 基本功能测试
        Predicate<String> predicate = inputString -> inputString.length() > 5;
        System.out.println(predicate.test("hello world"));

        /**
         * 定义一个集合，按照按照不同条件进行输出
         */
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        PredicateTest2 predicateTest2 = new PredicateTest2();

        predicateTest2.conditionFilter(list, item -> item % 2 == 0);
        System.out.println("---------");

        predicateTest2.conditionFilter(list, item -> item > 5);
        System.out.println("---------");

        // 打印集合中所有元素
        predicateTest2.conditionFilter(list, item -> true);
        System.out.println("---------");
    }

    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
    }

}