package com.gjxaiou.classloader;

/**
 * 演示方法，
 * VM参数添加 -XX:+TraceClassLoading 后，执行 MyTest5_2.main 方法，
 * MyParent5_2，MyChild5_2，MyParent5_2$1 都被加载
 * <p>
 * 在初始化一个类时，并不会先初始化它所实现的接口。当初始化一个接口时，不会先初始化它的父接口
 * <p>
 * 在使用 接口的、在编译期内确定值的 静态常量时，就不会去加载这个接口，更不会初始化这个接口
 * 在使用 接口的、在运行期内确定值的 静态常量时，就会去加载并初始化这个接口
 */


public class MyTest5_2 {
    public static void main(String[] args) {
        // 这里主动使用了 MyChild5_2，因此其会被初始化，但是 MyParent5_2 中代码没有执行，因此没有初始化它的接口
        System.out.println(MyChild5_2.b);
    }
}

interface MyParent5_2 {
    public static Thread thread = new Thread() {
        {
            System.out.println("MyParent5_2 invoked");
        }
    };
}

interface MyChild5_2 extends MyParent5_2 {
    // 输出：5
    public static int b = 5;

    // 执行词句输出：（因为常量值不确定，所以使用到该值的时候相当于初始化该结构
    // MyChild5_2 invoked
    // 3
    // public static final int b = new Random().nextInt(4);
    public static Thread thread = new Thread() {
        {
            System.out.println("MyChild5_2 invoked");
        }
    };
}