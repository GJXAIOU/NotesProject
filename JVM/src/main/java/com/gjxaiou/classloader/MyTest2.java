package com.gjxaiou.classloader;

/**
 * 编译期间能确定值的常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中
 * 运行期间才能确定值的常量在编译阶段不会存入到调用这个常量的方法所在的类的常量池中，而需要初始化这个常量所在的类
 * 本质上，调用类并没有直接引用到定义常量的类，因此并不会触发定义常量的类的初始化
 * 注意：这里指的是 MyParent2.str 这个常量存放到了MyTest2这个类的常量池中，之后MyTest2与MyParent2就没有任何关系了
 *       甚至，可以在运行MyTest2之前，将MyParent2的class文件删除（这个试验要在命令行里做，否则会重新编译运行代码），
 *       也不会影响运行结果
 */

public class MyTest2 {
    public static void main(String[] args) {
        System.out.println(MyParent2.str);
        // 运行结果
//        hello world
        System.out.println(MyParent2.s);
        System.out.println(MyParent2.i);
        System.out.println(MyParent2.m);
    }
}

class MyParent2 {
    public static final String str = "hello world";
    public static final short s = 7;
    public static final int i = 128;
    public static final int m = 1;

    static {
        System.out.println("MyParent2 static block");
    }
}

