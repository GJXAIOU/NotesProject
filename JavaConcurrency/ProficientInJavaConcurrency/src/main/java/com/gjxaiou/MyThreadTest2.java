package com.gjxaiou;

/**
 * @Author GJXAIOU
 * @Date 2020/2/15 16:00
 */
public class MyThreadTest2 {
    public static void main(String[] args) {
        // 使用此种方式输出为：hello,world
        MyClass myClass = new MyClass();
        Thread1 thread1 = new Thread1(myClass);
        Thread2 thread2 = new Thread2(myClass);

        // 测试方式二：
        // 如果使用以下代码代替上面代码，结果为 ：world,hello
//        MyClass myClass = new MyClass();
//        MyClass myClass1 = new MyClass();
//        Thread1 thread1 = new Thread1(myClass);
//        Thread2 thread2 = new Thread2(myClass1);

        thread1.start();
        // 休眠一段时间
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class MyClass {
    public synchronized void hello() {
        // thread1 首先进入 hello 方法，即是下面休眠了，但是不会释放对 myClass 对象的锁
        // 所以即是上面主线程在 700 毫秒之后恢复了，接着 thread2 启动，然后访问 world 方法，因为这时候 myClass 对象的锁还在 thread1 中，所以不能访问。
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello");
    }

    public synchronized void world() {
        System.out.println("world");
    }
}

// 定义两个线程类
class Thread1 extends Thread {
    private MyClass myClass;

    public Thread1(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.hello();
    }
}

class Thread2 extends Thread {
    private MyClass myClass;

    public Thread2(MyClass myClass) {
        this.myClass = myClass;
    }

    @Override
    public void run() {
        myClass.world();
    }
}