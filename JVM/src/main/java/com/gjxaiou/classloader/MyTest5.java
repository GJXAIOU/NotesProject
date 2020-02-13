package com.gjxaiou.classloader;

import java.util.Random;

/**
 * 如果在一个接口中声明一个常量（b = 5）,而且该常量在编译期就能完全确定好具体的数值，那么就不会加载这个接口，而是直接把这个常量值直接纳入了 MyTest5 的常量池中
 * 验证: 添加 VM options: -XX:+TraceClassLoading 之后，然后编译运行发现根本没有加载 MyParent5和 MyChild5，仅仅加载了 MyTest5，
 * 同时将两者的 class 文件删除之后仍然可以运行, 但是执行第二个打印语句的时候就加载了。
 *
 * @author GJXAIOU
 */
public class MyTest5 {
    public static void main(String[] args) {
        System.out.println(MyChild51.b);
       // System.out.println(MyChild52.c);
    }
}

interface MyParent5 {
    public static int a = 6;
}

interface MyChild51 extends MyParent5 {
    //接口属性默认是 public static final
    public static final int b = 5;
}

interface MyChild52 extends MyParent5 {
    public static final int c = new Random().nextInt(5);
}
