package com.gjxaiou.jdk8.lambdaAndFunctionInterface;


import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * 定义一个集合，按照按照不同条件进行输出
 */
public class PredicateTest2 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 4, 5, 6);

        PredicateTest2 predicateTest2 = new PredicateTest2();

        predicateTest2.conditionFilter(list, item -> item % 2 == 0);
        predicateTest2.conditionFilter(list, item -> item % 2 != 0);
        predicateTest2.conditionFilter(list, item -> item > 5);
        predicateTest2.conditionFilter(list, item -> item < 3);
        // 打印集合中所有元素
        predicateTest2.conditionFilter(list, item -> true);
        predicateTest2.conditionFilter(list, item -> false);
    }

    public void conditionFilter(List<Integer> list, Predicate<Integer> predicate) {
        for (Integer integer : list) {
            if (predicate.test(integer)) {
                System.out.println(integer);
            }
        }
        System.out.println("---------------");
    }

    // 原始方式，每个判定条件都需要定义新的方法
    public void findAllEvens(List<Integer> list) {
        for (Integer integer : list) {
            if (integer % 2 == 0) {
                System.out.println(integer);
            }
        }
    }
}