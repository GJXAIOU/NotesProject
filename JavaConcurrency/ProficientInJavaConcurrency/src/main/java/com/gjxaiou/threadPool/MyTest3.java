package com.gjxaiou.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class MyTest3 {
    public static void main(String[] args) {
        // 核心线程数为 3，最大为 5， 等待时间为 0， 阻塞队列长度为 3， 拒绝策略是直接抛异常
        ExecutorService executorService = new ThreadPoolExecutor(3, 5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(3),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 向线程池提交 9 个任务
        IntStream.range(0, 9).forEach(i -> {
            executorService.submit(() -> {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }

                IntStream.range(0, 2).forEach(j -> {
                    System.out.println(Thread.currentThread().getName());
                });
            });
        });

        // 关闭线程池
        executorService.shutdown();
    }
}