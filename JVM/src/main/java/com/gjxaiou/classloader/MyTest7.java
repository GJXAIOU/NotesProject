package com.gjxaiou.classloader;

/**
 * 验证类加载的双亲机制
 * java.lang.String 是由根加载器加载，在 rt.jar 包下
 */
public class MyTest7 {
    public static void main(String[] args) throws ClassNotFoundException {
        // 加载之前需要获取 class 对象
        Class<?> clazz = Class.forName("java.lang.String");
        // 返回针对该类的类加载器（就是实际加载该类的加载器），其中 null 表示启动类（根类）的加载器，
        // 这里返回 null，因为 java.lang.String 是由 bootstrap classloader加载
        System.out.println(clazz.getClassLoader());

        Class<?> clazz2 = Class.forName("com.gjxaiou.classloader.C");
        // 返回 sun.misc.Launcher$AppClassLoader@18b4aac2，C 由应用类加载器加载
        // 其中AppClassLoader:系统应用类加载器($前为外部类，后为内部类)
        System.out.println(clazz2.getClassLoader());
    }
}

class C {

}