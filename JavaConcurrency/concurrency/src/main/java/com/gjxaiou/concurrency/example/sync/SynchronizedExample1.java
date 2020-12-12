package com.gjxaiou.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    // 修饰一个代码块
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1 {} - {}", j, i);
            }
        }
    }

    // 修饰一个方法
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2 {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        // 如果不使用线程池，则一个对象调用两次同一个方法就是同时执行的，线程池相当于创建两个线程来执行该方法
        ExecutorService executorService = Executors.newCachedThreadPool();
        /**
         executorService.execute(() -> {
         example1.test1(1);
         });
         executorService.execute(() -> {
         example1.test1(1);
         });
         */

        /**
         executorService.execute(() -> {
         example1.test2(1);
         });
         executorService.execute(() -> {
         example1.test2(1);
         });
         */

        /**
         // 两个对象几乎交叉执行，因为作用于同一个对象，两个对象之间不相关
         executorService.execute(() -> {
         example1.test1(1);
         });
         executorService.execute(() -> {
         example2.test2(2);
         });

         */

        // 几乎同上，作用于调用对象
        executorService.execute(() -> {
            example1.test2(1);
        });
        executorService.execute(() -> {
            example2.test2(2);
        });
    }
}
