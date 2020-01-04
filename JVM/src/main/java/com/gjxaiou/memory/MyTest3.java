package com.gjxaiou.memory;

/**
 * @Author GJXAIOU
 * @Date 2019/12/11 18:03
 */
public class MyTest3 {
    public static void main(String[] args) {
        // 构造两个线程
        new Thread(() -> A.method(), "Thread-A").start();
        new Thread(() -> B.method(), "Thread-B").start();
    }
}

class A{
    // 线程进入到 synchronized 修饰的方法后，并且该方法是由 static 修饰的，则持有的不是当前类（Class A）对应的锁，而是当前类所对应的 Class
    // 对象的锁，所以不管该类有多少个实例或者对象，持有的都是一把锁
    public static synchronized  void method(){
        System.out.println("method from A");
        try {
            Thread.sleep(5000);
            B.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class B{
    public static synchronized void method(){
        System.out.println("method from B");
        try {
            Thread.sleep(5000);
            A.method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}