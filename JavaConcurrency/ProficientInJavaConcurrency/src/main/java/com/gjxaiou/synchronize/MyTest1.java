package com.gjxaiou.synchronize;

/**
 * 测试 synchronized 用法
 *
 * @Author GJXAIOU
 * @Date 2020/2/16 21:51
 */
public class MyTest1 {
    // 同步代码块（因为一个方法中可能只有几行需要上锁，所以关键字即可）
    private Object object = new Object();

    public void method() {
        // 表示对 object 对象上锁，当执行到这里的时候该线程会尝试获取 object 对象的锁，如果获取到就就行执行，如果获取不到就阻塞了。
        synchronized (object) {
            System.out.println("hello world");
        }
    }
}
