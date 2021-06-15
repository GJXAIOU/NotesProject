package com.gjxaiou.reentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author GJXAIOU
 * @Date 2021/3/19 16:59
 */
public class MyTest3 {

    public Lock lock = new ReentrantLock();

    public void myMethod1() {
        try {
            lock.lock();
            System.out.println("myMethod1 invoked");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();

        } finally {
            lock.unlock();
        }
    }

    public void myMethod2() {
        boolean result = false;
        try {
            result = lock.tryLock(800, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (result) {
            System.out.println("get the lock");
        } else {
            System.out.println("can't get the lock");
        }
    }

    public static void main(String[] args) {
        MyTest3 myTest3 = new MyTest3();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest3.myMethod1();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest3.myMethod2();
            }
        });

        t1.start();
        t2.start();

    }

}
