package com.liuxin.concurrency2;

/**
 * @author leany
 * @date 2020/11/9 13:12
 */
public class MyThreadTest2 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        /**
         * 结论：
         * 如果一个对象若干个synchronized方法，那么在某一个时刻只有一个synchronized方法被调用
         * 因为不同的synchronized的方法其锁都是一把锁（当前对象的锁，this），如果是静态方法的时候是当前对象的class
         */
        Thread thread1 = new Thread1(myClass);
        Thread thread2 = new Thread2(myClass);

        /**
         * 思考是先执行Thread1（中的方法）还是先执行Thread2（方法）
         * 即先打印world还是先打印hello
         */
        thread1.start();

        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread2.start();
    }

}


class MyClass {

    public synchronized void hello(){
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName()+" hello");
    }

    public synchronized void world(){
        System.out.println(Thread.currentThread().getName()+" word");
    }
}


/**
 * 该线程是打印hello
 */
class Thread1 extends Thread{

    private MyClass myclass;

    public Thread1(MyClass myclass) {
        this.myclass = myclass;
    }

    @Override
    public void run() {
        myclass.hello();
    }
}


/**
 * 该线程是打印world
 */
class Thread2 extends Thread{
    private MyClass myclass;

    public Thread2(MyClass myclass) {
        this.myclass = myclass;
    }

    @Override
    public void run() {
        myclass.world();
    }
}
