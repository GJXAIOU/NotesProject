package com.gjxaiou.aqs;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.IntStream;

/**
 * 验证使用 ReadWriteLock
 */
public class MyTest2 {
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void method() {
        try {
            // 获取到对象里面的一把读锁，然后上锁
            readWriteLock.readLock().lock();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method");
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        MyTest2 myTest2 = new MyTest2();
        IntStream.range(0, 5).forEach(i -> new Thread(myTest2::method).start());
    }
}
