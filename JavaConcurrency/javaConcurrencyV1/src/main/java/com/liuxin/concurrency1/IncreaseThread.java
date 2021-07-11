package com.liuxin.concurrency1;

/**
 * 实现递增的线程
 * @author leany
 * @date 2020/11/9 10:46
 */
public class IncreaseThread extends Thread{

    private MyObject myObject;

    public IncreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep((long)(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject.increase();
        }
    }
}
