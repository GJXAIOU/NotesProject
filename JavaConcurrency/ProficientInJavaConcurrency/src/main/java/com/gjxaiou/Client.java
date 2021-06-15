package com.gjxaiou;

/**
 * @Author GJXAIOU
 * @Date 2020/2/15 11:26
 */
public class Client {
    public static void main(String[] args) {
        // 只能创建一个对象，因为锁是加在对象上，如果两个线程操作两个对象就没有意义了MyObject myObject = new MyObject();
        MyObject myObject = new MyObject();
        Thread increaseThread = new IncreaseThread(myObject);
        Thread decreaseThread = new DecreaseThread(myObject);
        increaseThread.start();
        decreaseThread.start();
    }
}
