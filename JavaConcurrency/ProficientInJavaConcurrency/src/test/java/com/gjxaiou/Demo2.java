package com.gjxaiou;

public class Demo2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " 正在执行");
        }
    }

    public static void main(String[] args) {
        Demo2 demo2 = new Demo2();
        demo2.setName("myThread");
        demo2.start();

        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " 正在执行");
        }
    }
}
