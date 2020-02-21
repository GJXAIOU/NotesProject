package com.gjxaiou.lock;

/**
 * @Author GJXAIOU
 * @Date 2020/2/21 20:37
 */
public class MyTest1 {
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public void myMethod1() {
        synchronized (lock1) {
            synchronized (lock2) {
                System.out.println("myMethod1 invoked");
            }
        }
    }

    public void myMethod2() {
        synchronized (lock2) {
            synchronized (lock1) {
                System.out.println("myMethod2 invoked");
            }
        }
    }

    public static void main(String[] args) {
        final MyTest1 myTest1 = new MyTest1();

        Runnable runnable1 = new Runnable() {
            public void run() {
                while (true) {
                    myTest1.myMethod1();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread thread1 = new Thread(runnable1, "myThread1");
        Runnable runnable2 = new Runnable() {
            public void run() {
                while (true) {
                    myTest1.myMethod2();
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread thread2 = new Thread(runnable2, "myThread2");

        thread1.start();
        thread2.start();
    }
}
