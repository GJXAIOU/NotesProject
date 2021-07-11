package com.liuxin.concurrency2;

/**
 * 思考一下这段代码之后后的结果是什么样的。
 * @author leany
 * @date 2020/11/9 12:41
 */
public class MyThreadTest {

    public static void main(String[] args) {
        Runnable r = new MyThread();

        // 会执行同一个run方法。
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
    }
}


class MyThread implements Runnable{

    int x ;

    @Override
    public void run() {
        x = 0;

        while (true){
            System.out.println(Thread.currentThread().getName()+" result: "+ x++);

            try {
                Thread.sleep((long) (Math.random()*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (x == 30) {
                break;

            }
        }

    }
}
