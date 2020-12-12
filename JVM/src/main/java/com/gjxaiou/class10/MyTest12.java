package com.gjxaiou.class10;

/**
 * 调用 ClassLoader 类的 loadClass 方法并不是主动使用类，不会导致类的初始化
 */
public class MyTest12 {
    public static void main(String[] args) throws ClassNotFoundException {

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        // 该行代码执行，并不会导致 G 的初始化
        Class<?> loadClass = classLoader.loadClass("com.gjxaiou.class10.G");
        System.out.println(loadClass);
        System.out.println("-------------------------------");
        //反射会导致一个类的初始化(属于七种之一)
        Class<?> clazz = Class.forName("com.gjxaiou.class10.G");
        System.out.println(clazz);
    }
}

class G {
    static {
        System.out.println("G");
    }
}

/*
class com.gjxaiou.class10.G
-------------------------------
G
class com.gjxaiou.class10.G
 */