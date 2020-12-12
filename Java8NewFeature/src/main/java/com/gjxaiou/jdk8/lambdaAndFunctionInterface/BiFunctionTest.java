package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BiFunctionTest {
    public static void main(String[] args) {
        BiFunctionTest test = new BiFunctionTest();

        /**
         * 实现加减乘除操作
         */
        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 + value2));
        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 - value2));
        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 * value2));
        System.out.println(test.compute3(1, 2, (value1, value2) -> value1 / value2));

        // 25，测试 andThen
        System.out.println(test.compute4(2, 3, (value1, value2) -> value1 + value2,
                value -> value * value));

        // 根据指定函数遍历列表
        Person person1 = new Person("zhangSan", 20);
        Person person2 = new Person("liSi", 30);
        Person person3 = new Person("wangWu", 40);
        List<Person> persons = Arrays.asList(person1, person2, person3);

        List<Person> personResult = test.getPersonsByAge(20, persons, (age, personList) ->
                personList.stream().filter(person -> person.getAge() <= age).collect(Collectors.toList())
        );

        personResult.forEach(person -> System.out.println(person.getAge() + person.getUsername()));
    }

    // 输入两个参数，输出一个结果
    public int compute3(int a, int b, BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(a, b);
    }

    // 首先应用于当前函数，然后将结果作用于参数的 Function
    public int compute4(int a, int b, BiFunction<Integer, Integer, Integer> biFunction,
                        Function<Integer, Integer> function) {
        return biFunction.andThen(function).apply(a, b);
    }

    public List<Person> getPersonsByAge(int age, List<Person> persons, BiFunction<Integer,
            List<Person>, List<Person>> biFunction) {
        return biFunction.apply(age, persons);
    }
}
/**
 * output:
 * 3
 * -1
 * 2
 * 0
 * 25
 * 20zhangSan
 */
