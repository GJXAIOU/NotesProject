package com.gjxaiou;

/**
 * @Author GJXAIOU
 * @Date 2020/2/15 11:18
 */
public class IncreaseThread extends Thread {
    private MyObject myObject;

    public IncreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }

    // 重写 run 方法

    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            // 每次先让线程休眠 0 ~ 1 秒
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myObject.increase();
        }
    }
}
