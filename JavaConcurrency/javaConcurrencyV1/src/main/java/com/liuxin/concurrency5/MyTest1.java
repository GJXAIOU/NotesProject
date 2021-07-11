package com.liuxin.concurrency5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * CountDownLatch
 */
public class MyTest1 {
    public static void main(String[] args) {
        // 创建需要的等待的子线程数目
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 通过流的方式来实现子线程的创建（通过实现Runnable的方法）
        IntStream.range(0, 3).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(2000);
                // 通过运行的结果可以发现先执行完的居然是第2个线程
                System.out.println("he11o" + i);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                // 确保每次执行的最后都会将等待的子线程数量减少（这里可能需要将countDownLatch类中的方法好好的分析分析）
            } finally {
                countDownLatch.countDown();
            }

        }).start());

        System.out.println("启动子线程完毕");
        try {
            // 使用await方法等待直到子线程都执行完毕（防止进入一直等待的状态）
//            countDownLatch.await(100, TimeUnit.MILLISECONDS);
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程执行完毕");

    }
}
