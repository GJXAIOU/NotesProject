package com.gjxaiou.classloader;

/**
 * 验证
 * 调用ClassLoader类的loadClass方法加载一个类，并不是对类的主动使用，不会导致类的初始化
 * 反射会造成主动使用类
 */
class CL {
    static {
        System.out.println("Class CL");
    }
}

public class MyTest12 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();

        // 这行代码执行不会导致 CL 类的初始化
        Class<?> clazz = loader.loadClass("com.gjxaiou.classloader.CL");
        System.out.println(clazz);
        System.out.println("--------------");

        // 反射会导致CL类的初始化。。。这行代码执行会导致 CL 类的初始化
        clazz = Class.forName("com.gjxaiou.classloader.CL");
        System.out.println(clazz);
    }
}

/*
运行结果：
class zy.jvm.classloader.CL
--------------
Class CL
class zy.jvm.classloader.CL
*/
