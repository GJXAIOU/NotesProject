package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        /**
         * 创建一个包装了 "hello" 字符串的 Optional 容器对象
         * 因为 Optional 的构造方法是私有的，所以只能通过其的几个工厂方法来创建对象，如 of/ofNullable/empty 。
         */
        Optional<String> optional = Optional.ofNullable("hello");
        // 使用 ofNullable() 里面是 null 不会抛出异常
        Optional<String> optional2 = Optional.ofNullable(null);
        optional2.ifPresent(item -> System.out.println(item));
        System.out.println("-----------------");

        // 使用方式一（ isPresent() 不推荐）：
        if (optional.isPresent()) {
            System.out.println("取出方式一（不推荐）：" + optional.get());
        }

        // 推荐的 Optional 使用方式：ifPresent() + Lambda
        optional.ifPresent(item -> System.out.println("推荐的方式：" + item));
        System.out.println("-------");

        // optional 不为空则输出自身，如果没有值值输出后面的备选值
        System.out.println(optional.orElse("world"));
        System.out.println("---------");

        System.out.println(optional.orElseGet(() -> "niHao"));
    }
}
