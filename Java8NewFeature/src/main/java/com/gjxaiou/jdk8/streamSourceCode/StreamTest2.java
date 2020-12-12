package com.gjxaiou.jdk8.streamSourceCode;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamTest2 {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world", "hello world");

        NullPointerException nullPointerException = new NullPointerException("my exception");

        try (Stream<String> stream = list.stream()) {
            stream.onClose(() -> {
                System.out.println("aaa");
                // 情况一：  某一个关闭处理器抛出异常，不妨碍其他关闭处理器执行。如果抛出多个异常，第一个异常会传递给调用者，后面的异常都是压制的异常。
                // throw new NullPointerException("first exception");
                // 情况二：都抛出一样的异常：如果剩余的异常和抛出的第一个异常是同一个异常，则不会不压制，因为一个异常不能压制自己。
                // throw nullPointerException;
                // 情况三：这是两个异常，第一个会被抛出，然后第二个会被压制。
                throw new NullPointerException("first exception");
            }).onClose(() -> {
                System.out.println("bbb");
                // throw new ArithmeticException("second exception");
                // throw nullPointerException;
                throw new NullPointerException("second exception");
            }).forEach(System.out::println);
        }
    }
}
