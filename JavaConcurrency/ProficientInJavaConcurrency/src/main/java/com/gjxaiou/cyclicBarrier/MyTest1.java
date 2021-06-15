package com.gjxaiou.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyTest1 {
    public static void main(String[] args) {

        // 参数表示参与方，即只有 3 个线程都到达了屏障才能继续往下执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);

        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    // 每个线程随机睡眠一段时间
                    Thread.sleep((int) (Math.random() * 2000));

                    // 产生一个 0 ~ 500 之间的整数
                    int randomInt = new Random().nextInt(500);
                    System.out.println("hello" + randomInt);

                    // 检查一下是不是满足都到屏障了
                    // await 首先会等待屏障前是不是已经有三个线程了，如果没有则在屏障前等待。但是如果是第三个线程在调用 await 方法的一刹那会发现包括自己共有
                    // 3 个线程了则通知所有线程一起往下运行。当然唤醒的时候线程由先后顺序，所以每次执行 world + 随机数可能先后顺序都不一样。
                    cyclicBarrier.await();

                    System.out.println("world" + randomInt);

                } catch (InterruptedException | BrokenBarrierException exception) {
                    exception.printStackTrace();
                }
            }).start();
        }
    }
}
