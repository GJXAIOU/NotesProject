package com.liuxin.concurrency3;

/**
 * 锁粗化
 * JIT编译器在执行动态编译时，若发现前后相邻的synchronized块使用的是同一个锁对象，那么它就会把这几个synchronized块给合并为一个较大的同步块，
 * 这样做的好处在于线程在执行这些代码时，就无需频繁申请与释放锁了，从而达到申请与释放锁一次，就可以执行完全部的同步代码块，从而提升了性能。
 */
public class MyTest5 {
    private Object object = new Object();

    public void method(){
        // 如果object被共享的话，也就是放到属性中话。
        Object object = new Object();

        // 实际执行的时候是没有上锁的。
        synchronized (object){
            System.out.println("hello world");
        }

        synchronized (object){
            System.out.println("welcome");
        }

        synchronized (object){
            System.out.println("person");
        }
    }
}
