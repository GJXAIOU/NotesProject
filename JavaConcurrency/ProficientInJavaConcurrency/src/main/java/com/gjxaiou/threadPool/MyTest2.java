package com.gjxaiou.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MyTest2 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 5).forEach(i -> {
            executorService.submit(() -> {
                IntStream.range(0, 2).forEach(j -> {
                    System.out.println(Thread.currentThread().getName());
                });
            });
        });
        executorService.shutdown();
    }
}
