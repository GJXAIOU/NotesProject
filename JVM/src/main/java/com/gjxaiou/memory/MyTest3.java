package com.gjxaiou.memory;

/**
 * @Author GJXAIOU
 * @Date 2019/12/11 18:03
 */
public class MyTest3 {
    public static void main(String[] args) {
        // 构造两个线程
        // 步骤一：Thread-A 线程启动，执行 A.method（）方法，然后就会拿到类 A 对应的 Class 对象的锁，同时执行方法，睡眠，当执行到 B.method()
        //方法时候，发现该方法也是 synchronized 的，所以会尝试获取类 B 对应的 Class 对象对应的锁；
        new Thread(() -> A.method(), "Thread-A").start();
        // 步骤二：同时 Thread-B 线程启动，同上步骤就会形成死锁
        new Thread(() -> B.method(), "Thread-B").start();
    }
}

class A {
    // 线程进入到 synchronized 修饰的方法后，并且该方法是由 static 修饰的，则持有的不是当前类（Class A）对应的锁，而是当前类所对应的 Class
    // 对象的锁，所以不管该类有多少个实例或者对象，持有的都是一把锁
    public static synchronized void method() {
        System.out.println("method from A");
        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B {
    public static synchronized void method() {
        System.out.println("method from B");
        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}