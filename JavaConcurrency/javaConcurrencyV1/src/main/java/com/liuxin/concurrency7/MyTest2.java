package com.liuxin.concurrency7;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * CompletableFuture
 */
public class MyTest2 {
    public static void main(String[] args) {
        String result = CompletableFuture.supplyAsync(() -> "he11o").thenApplyAsync(value -> value + " world").join();
        System.out.println(result);
        System.out.println("=======");
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(value -> System.out.println("welcome" + value));
        System.out.println("=======");
        //对于stage的合并操作
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();

        System.out.println(result2);
        System.out.println("=======");

        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finished");
        });
        completableFuture.whenComplete((t, action) -> System.out.println("执行完成! "));
        System.out.println("主线程执行完毕");
        try {
            TimeUnit.MILLISECONDS.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }
}
