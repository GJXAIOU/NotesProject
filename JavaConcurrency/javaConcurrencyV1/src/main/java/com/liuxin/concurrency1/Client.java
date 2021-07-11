package com.liuxin.concurrency1;

/**
 * 编写一个多线程的程序，实现这样一个目标
 * 1.存在一个对象，该对象有一个int类型的成员变量counter，该成员变量的初始值为0
 * 2.创建两个线程，其中一个线程对该对象的成员变量counter+1，另一个线程对该对象的成员变量-1
 * 3.输出对象成员变量counter每次变化之后的值
 * 4.最终输出的结果应为：1010101010101010
 * @author leany
 * @date 2020/11/9 10:51
 */
public class Client {

    public static void main(String[] args) {
        MyObject myObject = new MyObject();


        Thread increaseThread1 = new IncreaseThread(myObject);
        Thread increaseThread2 = new IncreaseThread(myObject);
        Thread decreaseThread1 = new DecreaseThread(myObject);
        Thread decreaseThread2 = new DecreaseThread(myObject);

        // 在MyObject类的方法中将if进行更改将得到不一样的结果
        increaseThread1.setName("increase1");
        increaseThread2.setName("increase2");
        decreaseThread1.setName("decrease1");
        decreaseThread2.setName("decrease2");

        increaseThread1.start();
        increaseThread2.start();
        decreaseThread1.start();
        decreaseThread2.start();
    }
}
