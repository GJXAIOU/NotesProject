package com.gjxaiou.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author GJXAIOU
 * @Date 2021/3/19 15:38
 */
public class MyTest1 {

    // 先定义锁的对象实例
    public Lock lock = new ReentrantLock();

    public void myMethod1(){
        try {
            // 首先尝试获取锁
            lock.lock();
            System.out.println("myMethod1 invoked");
        } finally {
            // 如果注释该行代码，则只执行 method1，并且输出完之后 JVM 不退出
            lock.unlock();
        }
    }

    public void myMethod2(){
        try {
            // 首先尝试获取锁
            lock.lock();
            System.out.println("myMethod2 invoked");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
         MyTest1 myTest1 = new MyTest1();
        // 分别构建两个线程对象，分别访问上面两个方法

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest1.myMethod1();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                myTest1.myMethod2();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

    }
}
