package com.liuxin.concurrency5;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * cyclicBarrier
 * 关于cyclicBarrier的底层执行流程
 * 1.初始化CyclicBarrier中的各种成员变量，包括parties、 count以及Runnable (可选)
 * 2.当调用await方法时，底层会先检查计数器是否已经归零，如果是的话，那么就首先执行可选的Runnable,接下来开始下一个generation;
 * 3.在下一个分代中，将会重置count值为parties,并且创建新的Generation实例。
 * 4.同时会调用Condition的s ignalA1l方法，唤醒所有在屏障前面等待的线程，让其开始继续执行。
 * 5.如果计数器没有归零，那么当前的调用线程将会通过Condition的await方法，在屏障前进行等待。
 * 6.以上所有执行流程均在lock锁的控制范围内，不会出现并发情况。（公用的数据是count，通过lock来实现和避免多线程的情况）
 */
public class MyTest2 {
    public static void main(String[] args) {
        // 第二个参数可以等待3个线程都到达之后然后收集相关的数据
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3,()->{
            System.out.println("get info with five thread");
        });
        // cyclicBarrier最大的特点就是当所有线程通过之后又会产生新的屏障，实现了屏障的可重复使用
        for(int j = 0;j<2;j++) {
            for (int i = 0; i < 3; ++i) {
                new Thread(() -> {
                    try {
                        // 随机睡眠的时间
                        Thread.sleep((long) (Math.random() * 2000));
                        // 获取一个随机数字
                        int randomInt = new Random().nextInt(500);
                        System.out.println("before barrier" + randomInt);
                        // 重载的方法，如果等待超时就直接破坏掉这个屏障，然后后面的线程到达屏障之后就会出现屏障破坏的异常
//                    cyclicBarrier.await(200, TimeUnit.MILLISECONDS);
                        cyclicBarrier.await();
                        System.out.println("after barrier" + randomInt);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }).start();

            }
        }
    }
}