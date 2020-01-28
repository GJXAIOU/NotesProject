package com.gjxaiou.classloader;

public class MyTest13 {
    public static void main(String[] args) {
        // ClassLoader.getSystemClassLoader() 获取系统类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);

        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}
/* 运行结果：
sun.misc.Launcher$AppClassLoader@659e0bfd
sun.misc.Launcher$ExtClassLoader@6d06d69c
null
*/
