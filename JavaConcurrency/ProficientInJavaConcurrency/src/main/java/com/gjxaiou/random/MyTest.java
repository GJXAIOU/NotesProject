package com.gjxaiou.random;

import java.util.Random;
import java.util.stream.IntStream;

public class MyTest {
    public static void main(String[] args) {
        Random random = new Random();
        IntStream.range(0, 10).forEach(i -> {
            System.out.println(random.nextInt(10));
        });
    }
}
