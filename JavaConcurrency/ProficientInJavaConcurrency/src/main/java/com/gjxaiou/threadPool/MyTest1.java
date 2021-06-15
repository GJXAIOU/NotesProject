package com.gjxaiou.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class MyTest1 {
    public static void main(String[] args) {
        /**
         * 创建线程池
         * 方式一：直接 new ThreadPoolExecutor
         * 方式二：通过 Executors 创建
         */

        // 创建一个包括 3 个线程的固定大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        // submit 中是待执行的任务
        executorService.submit(() -> {
            // 循环 5 次输出当前线程名称Ex
            IntStream.range(0, 5).forEach(i -> {
                System.out.println(Thread.currentThread().getName());
            });
        });
    }
}
