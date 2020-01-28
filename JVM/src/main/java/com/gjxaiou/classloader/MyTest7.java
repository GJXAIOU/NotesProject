package com.gjxaiou.classloader;

/**
 * 验证类加载的双亲机制
 */
public class MyTest7 {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> clazz = Class.forName("java.lang.String");
        // 返回 null，因为 java.lang.String 是由 bootstrap classloader加载
        System.out.println(clazz.getClassLoader());

        Class<?> clazz2 = Class.forName("com.gjxaiou.classloader.C");
        // 返回 sun.misc.Launcher$AppClassLoader@18b4aac2，C 由应用类加载器加载
        System.out.println(clazz2.getClassLoader());
    }
}

class C {

}