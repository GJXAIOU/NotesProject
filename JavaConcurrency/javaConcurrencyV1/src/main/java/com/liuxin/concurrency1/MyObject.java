package com.liuxin.concurrency1;

/**
 * 递增和递减具体实现的方法类
 * @author leany
 * @date 2020/11/9 10:38
 */
public class MyObject {

    private int counter = 0;


    /**
     * 这里实现的方法是通过对应的线程来调用实际的方法
     * 比如这里调用递增的方法
     */
    public synchronized void increase(){
        // 如果这里将while改成if的话此时只能满足两个线程进行交替输出
        while (counter != 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter++;
        System.out.println(Thread.currentThread().getName() +": "+ counter);
        notify();
    }

    /**
     * 实际的递减方法
     */
    public synchronized void decrease(){
        // 如果这里将while改成if的话此时只能满足两个线程进行交替输出
        while (counter == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.println(Thread.currentThread().getName()+": "+ counter);
        notify();
    }
}
