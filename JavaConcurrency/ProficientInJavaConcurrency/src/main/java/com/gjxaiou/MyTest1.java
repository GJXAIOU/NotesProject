package com.gjxaiou;

/**
 * @Author GJXAIOU
 * @Date 2020/2/14 11:23
 */

/**
 * 在调用 wait 方法时，线程必须要持有被调用对象的锁，当调用 wait 方法后，线程就会释放掉该对象的锁
 * 在调用 Thread 类的 sleep 方法时候，线程是不会释放掉对象的锁的。
 */


public class MyTest1 {
    public static void main(String[] args) throws InterruptedException {

        Object object = new Object();
        // 测试：线程必须用于对象的锁才能调用 wait 方法，如果直接调用会报错
        // Exception in thread "main" java.lang.IllegalMonitorStateException
        // 即当前的线程一定要持有调用 wait 对象（这里是 object 对象）的锁才可以
        // 解决方法：可以将调用 wait 方法放入 synchronized 同步代码块，因为进入代码块中就相当于获取到对象的锁了
        // object.wait();
        synchronized (object) {
            // 进入代码块相当于已经获取到 object 对象的锁
            object.wait();
        }

    }
}
