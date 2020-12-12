package com.gjxaiou.synchronize;

/**
 * @Author GJXAIOU
 * @Date 2020/2/20 21:44
 */
public class MyTest5 {
    // 如果声明为成员变量,就被所有访问同一个 MyTest5 实例的线程所共享，所以同一时刻只有一个对象可以获取到 object 对象的锁。
    // 这种代码，每一个 synchronized 代码块线程都要争锁，浪费时间。
    Object object = new Object();

    public void method() {
        // 因为编译器使用 锁消除，相当于下面的 synchronized 关键字都不存在了，也就不需要锁获取与消除了，就是直接执行。
        // Object object = new Object();
        synchronized (object) {
            System.out.println("hello");
        }
        synchronized (object) {
            System.out.println("world");
        }
        synchronized (object) {
            System.out.println("person");
        }
    }
}
