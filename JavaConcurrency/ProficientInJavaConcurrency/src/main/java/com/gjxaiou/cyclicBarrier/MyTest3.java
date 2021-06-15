package com.gjxaiou.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MyTest3 {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
            System.out.println("hello  world  GJXAIOU");
        });
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep((int) (Math.random() * 2000));
                    int randomInt = new Random().nextInt(500);
                    System.out.println("hello" + randomInt);

                    cyclicBarrier.await(20, TimeUnit.MILLISECONDS);

                    System.out.println("world" + randomInt);
                } catch (InterruptedException | BrokenBarrierException exception) {
                    exception.printStackTrace();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
