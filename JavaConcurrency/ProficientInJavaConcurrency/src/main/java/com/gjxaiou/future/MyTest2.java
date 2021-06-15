package com.gjxaiou.future;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 需要 Callable 对象封装任务执行逻辑
 * 用 FutureTask 包装任务
 * 启动一个线程来执行包装后的任务
 * 从主程序中调用 FutureTask 中 get 方法获取执行结果
 */
public class MyTest2 {
    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println("pre execution");
            // 子线程睡眠 5s
            Thread.sleep(5000);
            int randomNumber = new Random().nextInt(500);
            System.out.println("post execution");

            return randomNumber;
        };

        FutureTask futureTask = new FutureTask<Integer>(callable);

        new Thread(futureTask).start();

        System.out.println("thread has started");

        try {
            // 主线程睡眠 2s
            Thread.sleep(2000);
            // 主线程等待 1s
            System.out.println(futureTask.get(1, TimeUnit.MILLISECONDS));
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
