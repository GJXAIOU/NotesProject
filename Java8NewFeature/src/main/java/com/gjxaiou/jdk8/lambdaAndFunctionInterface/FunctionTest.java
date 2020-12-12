package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.function.Function;

public class FunctionTest {

    // 接收一个 Function 类型的参数，该函数式接口中有唯一的抽象方法： R apply(T t);
    public int compute(int a, Function<Integer, Integer> function) {
        // apply() 这个动作行为是使用者进行传递的
        int result = function.apply(a);
        return result;
    }

    // Function 中输入一个整型，输出一个字符串
    public String convert(int a, Function<Integer, String> function) {
        return function.apply(a);
    }

    /**
     * 之前的实现方法，需要将每一个行为都提前定义好，然后调用。
     */
    public int method1(int a) {
        return 2 * a;
    }

    public int method2(int a) {
        return 5 + a;
    }


    public static void main(String[] args) {
        FunctionTest test = new FunctionTest();

        // 将一个行为：value -> {return 2 * value;} 作为参数进行传递（对应的就是 apply()），该行为中输入参数为 value，输出为： 2 * value
        System.out.println(test.compute(1, value -> {
            return 2 * value;
        }));
        System.out.println(test.compute(2, value -> 5 + value));

        System.out.println(test.convert(5, value -> value + "hello world"));

        System.out.println(test.method1(2));

        /**
         * 行为是调用的时候才确定的，当然可以先定义好 Lambda 表达式然后传递
         */
        Function<Integer, Integer> function = value -> value * 2;

        System.out.println(test.compute(4, function));
    }
}