package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CollectionTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);

        // 方式一：遍历集合方式
        for (int i = 0; i < list.size(); ++i) {
            System.out.println(list.get(i));
        }
        System.out.println("----------");

        // 方式二：增强 for 循环
        for (Integer i : list) {
            System.out.println(i);
        }
        System.out.println("----------");

        // 方式三：使用 Consumer 函数式接口
        // forEach() 是 Iterable 接口的默认实现方法，则其实现类就继承了该方法，List 是 Iterable 的一个实现类
        // Consumer 函数式接口中的 JavaDoc 文档说明：接收另一个参数并且不返回结果值，可能会修改入参值。
        list.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        });
        System.out.println("----------");

        // 方式三改为使用 Lambda 表达式
        list.forEach(integer -> {
            System.out.println(integer);
        });
        System.out.println("----------");

        // 方式四：使用方法引用（method reference），因为函数式接口的实例可以通过 Lambda 表达式、方法引用、构造方法引用来创建。
        list.forEach(System.out::println);
    }
}
