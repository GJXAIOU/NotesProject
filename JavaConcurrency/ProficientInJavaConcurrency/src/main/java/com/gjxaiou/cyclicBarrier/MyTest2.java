package com.gjxaiou.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyTest2 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
        for (int n = 0; n < 2; n++) {
            for (int i = 0; i < 3; i++) {
                new Thread(() -> {
                    try {
                        Thread.sleep((int) (Math.random() * 2000));
                        int randomInt = new Random().nextInt(500);
                        System.out.println("hello" + randomInt);

                        cyclicBarrier.await();

                        System.out.println("world" + randomInt);
                    } catch (InterruptedException | BrokenBarrierException exception) {
                        exception.printStackTrace();
                    }
                }).start();
            }
        }
    }
}
