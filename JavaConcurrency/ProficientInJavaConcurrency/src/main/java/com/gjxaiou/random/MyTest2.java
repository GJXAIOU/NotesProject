package com.gjxaiou.random;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class MyTest2 {
    public static void main(String[] args) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        IntStream.range(0, 10).forEach(i -> {
            System.out.println(threadLocalRandom.nextInt(10));
        });
    }
}
