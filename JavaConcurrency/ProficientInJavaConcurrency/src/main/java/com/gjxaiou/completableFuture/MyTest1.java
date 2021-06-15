package com.gjxaiou.completableFuture;

import java.time.temporal.ValueRange;
import java.util.concurrent.CompletableFuture;

public class MyTest1 {
    public static void main(String[] args) {
        // 体现 CompletableFuture 的 stage 功能  => 对结果进行转换
        String result =
                CompletableFuture.supplyAsync(() -> "hello").thenApplyAsync(value -> value +
                        " world").join();

        System.out.println(result);

        System.out.println("================");

        // 对结果进行消费
        CompletableFuture.supplyAsync(() -> "hello").thenAccept(value -> System.out.println(
                " welcome" + value));

        System.out.println("=================");

        // 两个操作都是异步的，相互独立执行，然后使用 thenCombine 将两个结果进行组合
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            return "world";
            // thenCombine 第二个参数为 BiFunction,即输入两个，输出一个，对应下面的结构。本质上是对上面两个返回结果进行合并
        }), (s1, s2) -> s1 + " " + s2).join();

        System.out.println(result2);


        System.out.println("======重要重要========");
        /*
         首先会执行回调  completableFuture.whenComplete 但是因为该线程还在休眠中
         2s，并且该方法非阻塞，因此主线程会继续往下走，所以首先打印“主线程执行完毕”。
         然后主线程在 7s 那里进行了休眠。
         然后 2s 时间过了之后执行的任务执行完了，打印“task finished”
        然后 whenComplete 回调进行执行，打印“执行完成”
         */


        // get 不阻塞
        CompletableFuture completableFuture = CompletableFuture.runAsync(() -> {
            try {
                // 模拟这是一个相对耗时的任务，这里面就是真正要执行的任务
                Thread.sleep(2000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("task finished");
        });

        // 不阻塞主线程主线程。
        completableFuture.whenComplete((t, action) -> System.out.println("执行完成"));

        System.out.println("主线程执行完毕");
        try {
            Thread.sleep(7000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
    }
}
