package com.gjxaiou.classloader;

import sun.misc.Launcher;

public class MyTest18 {
    public static void main(String[] args) {
        // 启动类加载器默认的加载的类的路径
        System.out.println(System.getProperty("sun.boot.class.path"));
        // 扩展类加载器默认的加载的类的路径
        System.out.println(System.getProperty("java.ext.dirs"));
        // 系统类加载器默认的加载的类的路径
        System.out.println(System.getProperty("java.class.path"));

        System.out.println(ClassLoader.class.getClassLoader());
        // 上一行的运行结果是 null

        System.out.println(Launcher.class.getClassLoader());
        // 上一行的运行结果是 null
        // 因为AppClassLoader和ExtClassLoader是位于Launcher内的静态内部类，既然Launcher类是由启动类加载器加载，
        // 那么AppClassLoader和ExtClassLoader也是由启动类加载器所加载。

        System.out.println("---------------------------");

        // 下面的代码和运行方式是 用来说明 自定义系统类加载器 的方法
        System.out.println(System.getProperty("java.system.class.loader"));
        // 上一行的运行结果是 null。 说明默认情况下，java.system.class.loader 没有被定义
        //
        // 在 MyTest16中追加 只有ClassLoader类型的参数的构造函数
        // public MyTest16(ClassLoader parent) {
        //        super(parent);
        //    }
        // 运行命令 java -Djava.system.class.loader=zy.jvm.classloader.MyTest16 zy.jvm.classloader.MyTest18
        // 指定了哪个类作为系统类加载器
        /*
zy.jvm.classloader.MyTest16
         */
        System.out.println(MyTest18.class.getClassLoader());
        // IDEA直接运行和像上面一样用java命令运行，上一行的运行结果是 sun.misc.Launcher$AppClassLoader@135fbaa4。
        System.out.println(MyTest16.class.getClassLoader());
        // IDEA直接运行和像上面一样用java命令运行，上一行的运行结果是 sun.misc.Launcher$AppClassLoader@135fbaa4。
        System.out.println(ClassLoader.getSystemClassLoader());
        // IDEA直接运行，上一行的运行结果是 sun.misc.Launcher$AppClassLoader@135fbaa4。
        // 像上面一样用java命令运行的结果是 zy.jvm.classloader.MyTest16@4e25154f

    }
}
