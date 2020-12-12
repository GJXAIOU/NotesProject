package com.gjxaiou.jdk8.lambdaAndFunctionInterface;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * 定义两个函数式接口
 */
@FunctionalInterface
interface TheInterface {
    void myMethod();
}

@FunctionalInterface
interface TheInterface2 {
    void myMethod2();
}

public class Test3 {
    public static void main(String[] args) {
        /**
         * 验证：Lambda 表达式是属于什么类型必须通过上下文信息来断定，这里的表达式：() -> {}; 需要前面的引用类型才能判断类型。
         */
        // 因为抽象方法不接受参数不返回值。
        TheInterface i1 = () -> {
        };
        System.out.println(i1.getClass().getInterfaces()[0]);

        TheInterface2 i2 = () -> {
        };
        System.out.println(i2.getClass().getInterfaces()[0]);

        /**
         * 示例二：创建线程方式，这里使用 Lambda 形式来实现 Runnable 接口的形式。
         */
        new Thread(() -> System.out.println("hello world")).start();


        /**
         * 示例三：列表遍历修改
         */
        // 需求一：遍历列表然后将元素全部大写然后输出
        List<String> list = Arrays.asList("hello", "world", "hello world");
        // forEach 中接受一个 Consumer 接口，该接口接受一个参数并且不返回值。
        list.forEach(item -> System.out.println(item.toUpperCase()));


        // 需求二：遍历 list1 得到值然后添加到目标集合 list2 中，然后打印
        List<String> list2 = new ArrayList<>();
        list.forEach(item -> list2.add(item.toUpperCase()));
        list2.forEach(item -> System.out.println(item));

        // 需求二的语句简化：使用 stream 流的形式的两种写法
        list.stream().map(item -> item.toUpperCase()).forEach(item -> System.out.println(item));
        // map() 本身接收的是一个函数式接口 Function 的参数，该函数式接口的抽象方法为接收一个参数然后返回一个值，除了使用上面的 Lambda
        // 表达式之外还可以使用下面的方法引用。
        list.stream().map(String::toUpperCase).forEach(item -> System.out.println(item));

        // 方法引用，Function 函数式接口
        // 因为 toUpperCase() 方法是一个实例方法，因此使用的时候肯定是有一个 String 对象来调用该方法。对于像 String::toUpperCase 这种
        // 类::实例方法 形式的方法引用，则该实例方法的第一个输入参数一定就是调用该方法的对象，这里就是 String 对象，正好对应 Lambda 表达式中的第一个参数（对应上面
        // Lambda 表达式写法就是 item，也就是 Function 的 String 类型来源）。
        Function<String, String> function = String::toUpperCase;
        System.out.println(function.getClass().getInterfaces()[0]);
    }
}
/** output:
 * interface com.gjxaiou.jdk8.lambdaAndFunctionInterface.TheInterface
 * interface com.gjxaiou.jdk8.lambdaAndFunctionInterface.TheInterface2
 * hello world
 * HELLO
 * WORLD
 * HELLO WORLD
 * HELLO
 * WORLD
 * HELLO WORLD
 * HELLO
 * WORLD
 * HELLO WORLD
 * HELLO
 * WORLD
 * HELLO WORLD
 * interface java.util.function.Function
 *
 * Process finished with exit code 0
 */