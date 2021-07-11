package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.function.Function;

public class FunctionTest2 {
    public static void main(String[] args) {
        FunctionTest2 test = new FunctionTest2();
        // 先应用后面，然后应用前面
        System.out.println(test.compute(1, value -> value + " hello", value -> value + " world"));
        // 先应用前面，然后应用后面
        System.out.println(test.compute2(2, value -> value + " hello ", value -> value + "world"));
    }

    // 使用 compose，先对输入参数应用 function2 的 apply(),然后将结果作为 function1 的 apply() 的输入
    public String compute(int a, Function<String, String> function1,
                          Function<Integer, String> function2) {
        return function1.compose(function2).apply(a);
    }

    // 使用 andThen
    public String compute2(int a, Function<Integer, String> function1,
                           Function<String, String> function2) {
        return function1.andThen(function2).apply(a);
    }
}