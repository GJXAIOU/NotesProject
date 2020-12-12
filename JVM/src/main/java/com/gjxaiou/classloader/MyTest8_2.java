package com.gjxaiou.classloader;

/**
 * @Author GJXAIOU
 * @Date 2020/2/13 12:06
 */
public class MyTest8_2 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        // 不会初始化
        Class<?> clazz1 = loader.loadClass("com.gjxaiou.classloader.CL1");
        // 输出：class com.gjxaiou.classloader.CL1
        System.out.println(clazz1);
        System.out.println("-------------------");

        Class<?> clazz = Class.forName("com.gjxaiou.classloader.CL1");
        // 反射初始化
        // 输出：FinalTest static block
        //class com.gjxaiou.classloader.CL1
        System.out.println(clazz);
    }
}

class CL1 {
    static {
        System.out.println("FinalTest static block");
    }
}



