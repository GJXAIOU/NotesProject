package com.gjxaiou.synchronize;

/**
 * @Author GJXAIOU
 * @Date 2020/2/20 21:30
 */
public class MyTest4 {
    // 在 MyTest4 只有一个实例的情况下，这个实例的成员变量会被所有线程所共享
    // private Object object = new Object();

    public void method() {
        // 局部变量，每个线程都会有该 object 对象的引用，所以加不加 synchronized 没什么意义了
        Object object = new Object();
        synchronized (object) {
            System.out.println("hello world");
        }
    }
}
