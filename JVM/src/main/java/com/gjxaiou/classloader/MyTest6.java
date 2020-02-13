package com.gjxaiou.classloader;

/**
 * public static int counter2 = 0; 的位置对结果的影响：
 * 当位于 private Singleton() 之前，则结果为 counter1: 1  counter2: 1
 * 当位于 private Singleton() 之后，则结果为 counter1: 1  counter2: 0
 * <p>
 * 因调用了类的静态变量或静态方法，属于主动使用类，该类会被初始化。
 * 类在连接过程中，要先经历准备阶段，这时类的静态变量会被初始化为默认值，本例中 Singleton 的 counter1 和 counter2 都被初始化为 0。
 * 而在类的初始化阶段，类的静态变量赋值语句和构造方法按代码编写顺序进行执行，
 * 本例中 Singleton 的 counter1 和 counter2 在准备阶段被赋 0，初始化阶段时，没有对 counter1 赋值，
 * 而构造方法中 counter1 和 counter2 都被加 1，都变成 1，而之后再执行把 counter2 设置为 0 的赋值语句。
 * 这就造成了 counter1: 1  counter2: 0 的结果。
 */
public class MyTest6 {
    public static void main(String[] args) {
        // 首先调用类 Singleton 的静态方法，表示对该类的主动使用
        Singleton singleton = Singleton.getInstance();

        System.out.println("counter1: " + Singleton.counter1);
        System.out.println("counter2: " + Singleton.counter2);
    }
}

class Singleton {
    public static int counter1;
    public static int counter2 = 0;
    private static Singleton singleton = new Singleton();

    private Singleton() {
        counter1++;
        counter2++;
        System.out.println("构造方法内：counter1=" + counter1 + ", counter2=" + counter2);
    }

    // 若改变此赋值语句的位置，输出  1，0
    // public static int counter2 = 0;

    public static Singleton getInstance() {
        return singleton;
    }
}

