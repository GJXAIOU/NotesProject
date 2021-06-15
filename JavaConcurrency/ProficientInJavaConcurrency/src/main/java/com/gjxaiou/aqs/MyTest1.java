package com.gjxaiou.aqs;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class MyTest1 {
    private Lock lock = new ReentrantLock();

    public void method() {
        try {
            lock.lock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
            System.out.println("method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        // 创建 5 个线程执行上面方法
        MyTest1 myTest1 = new MyTest1();

        IntStream.range(0, 5).forEach(i -> {
            new Thread(() -> {
                myTest1.method();
            }).start();
        });
    }

}
