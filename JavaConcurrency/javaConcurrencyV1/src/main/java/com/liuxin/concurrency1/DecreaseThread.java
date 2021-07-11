package com.liuxin.concurrency1;

/**
 * 递减的线程
 * @author leany
 * @date 2020/11/9 10:49
 */
public class DecreaseThread extends Thread{

    private MyObject myObject;

    public DecreaseThread(MyObject myObject) {
        this.myObject = myObject;
    }


    /**
     * 该线程的操作是实现递减的效果
     */
    @Override
    public void run() {
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep((long)(Math.random()*10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 写的时候这里犯了一个很严重的错误（不过有的时候小小的错误也可以另外的结果），就是把这个方法写在外面了，然后
            // 出现递增的线程一直处于等待的状态，没有人给他解锁（阻塞的状态）
            myObject.decrease();
        }
    }
}
