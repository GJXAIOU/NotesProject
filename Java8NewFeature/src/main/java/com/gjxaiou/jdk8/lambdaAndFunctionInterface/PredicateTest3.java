package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author GJXAIOU
 * @Date 2020/10/24 14:41
 */
public class PredicateTest3 {

    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        PredicateTest3 predicateTest2 = new PredicateTest3();

        predicateTest2.conditionFilter2(list, item -> item > 5, item -> item % 2 == 0);
        System.out.println("---------");

        System.out.println(predicateTest2.isEqual(new Date()).test(new Date()));
    }

    public void conditionFilter2(List<Integer> list, Predicate<Integer> predicate,
                                 Predicate<Integer> predicate2) {
        for (Integer integer : list) {
            if (predicate.and(predicate2).negate().test(integer)) {
                System.out.println(integer);
            }
        }
    }

    public Predicate<Date> isEqual(Object object) {
        return Predicate.isEqual(object);
    }
}