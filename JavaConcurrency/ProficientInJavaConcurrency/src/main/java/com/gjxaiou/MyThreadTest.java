package com.gjxaiou;

/**
 * @Author GJXAIOU
 * @Date 2020/2/15 15:16
 */
public class MyThreadTest {
    public static void main(String[] args) {
        Runnable thread = new MyThread();
        // 因为创建线程 thread1 和 thread2 时候传入的是同一个 Runnable 实例对象
        Thread thread1 = new Thread(thread);
        Thread thread2 = new Thread(thread);
        // 因此当两个线程启动 start 方法时候都会去执行同一个 MyThread 对象里面的 run 方法
        thread1.start();
        thread2.start();
    }
}

// 该线程类实现了 Runnable 接口
class MyThread implements Runnable {
    int x;

    public void run() {
        x = 0;
        while (true) {
            System.out.println("result:" + x++);

            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == 30) {
                break;
            }
        }
    }
}