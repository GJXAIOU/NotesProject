package com.gjxaiou;

public class Demo extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(30000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getPriority());
    }

    public static void main(String[] args) {
        System.out.println("main 方法中的线程 " + Thread.currentThread().getName());
        Thread.currentThread().setPriority(3);
        Demo demo = new Demo();
        demo.start();
    }
}
