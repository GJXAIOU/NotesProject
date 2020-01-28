package com.gjxaiou.classloader;

public class MyTest24 {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        // 上一行运行结果是
        // sun.misc.Launcher$AppClassLoader@135fbaa4
        System.out.println(Thread.class.getClassLoader());
        // 上一行运行结果是  java.lang.Thread由启动类加载器加载，
        // null
    }
}
