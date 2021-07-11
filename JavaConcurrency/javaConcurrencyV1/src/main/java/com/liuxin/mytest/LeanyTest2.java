package com.liuxin.mytest;


import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liuxin9619@gmail.com
 * @date 2021/2/1 11:28
 */
public class LeanyTest2 {
    static int number = 0;  //共享数据 number
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                //有多条语句操作共享数据
                int n = 10000;
                while(n>0){
                    number = number + 1;
                    atomicInteger.addAndGet(1);
                    n--;
                }
            }
        };
        //多线程环境
        Thread t1  = new Thread(r);
        Thread t2  = new Thread(r);

        t1.start();
        t2.start();

        try {
            //等待足够长的时间 确保上述线程均执行完毕
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number);
        System.out.println(atomicInteger.intValue());
    }
}
