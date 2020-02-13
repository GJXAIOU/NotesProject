package com.gjxaiou.classloader;

public class MyTest13 {
    public static void main(String[] args) {
        // ClassLoader.getSystemClassLoader() 获取系统类加载器
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("默认使用:" + classLoader);
        System.out.println("类加载器层次结构:");
        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}
/* 运行结果：
默认使用:sun.misc.Launcher$AppClassLoader@18b4aac2
类加载器层次结构:
sun.misc.Launcher$ExtClassLoader@677327b6
null
*/
