package com.gjxaiou.classloader;

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

class CL {
    static {
        System.out.println("Class CL");
    }
}

/*
运行结果：
class com.gjxaiou.classloader.CL
--------------
Class CL
class com.gjxaiou.classloader.CL
*/
