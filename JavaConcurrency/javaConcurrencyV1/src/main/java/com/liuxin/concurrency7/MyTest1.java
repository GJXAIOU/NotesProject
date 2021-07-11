package com.liuxin.concurrency7;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * FutureTask的使用（未来任务的使用）
 */
public class MyTest1 {
    public static void main(String[] args) {
        Callable<Integer> callable = () -> {
            System.out.println("pre execution");
            Thread.sleep(5000);
            int randomNumber = new Random().nextInt(500);
            System.out.println("post execution");
            return randomNumber;
        };
        FutureTask<Integer> futureTask = new FutureTask(callable);
        new Thread(futureTask).start();
        System.out.println("thread has started");
        try {
            Thread.sleep(2000);
//            System.out.println(futureTask.get());
            /*
             * 注意这里get方法具有阻塞的的作用，如果使用下面重载的方法进行获取的话，当等待的时间小于任务执行的时间
             * 那么此时就会抛异常（timeout）但是并不会影响子线程的执行。（两个线程之间是异步的关系）
             */
            System.out.println(futureTask.get(1, TimeUnit.MILLISECONDS));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("thread has ended");

    }
}
