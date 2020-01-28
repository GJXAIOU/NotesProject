package com.gjxaiou.classloader;

public class MyTest17 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");

        Class<?> clazz = loader1.loadClass("zy.jvm.classloader.MySample");
        System.out.println("class: " + clazz.hashCode());
        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat主动使用，这里就不会加载MyCat Class
//        Object object = clazz.newInstance();

    }
}
// 运行结果   类由系统类加载器加载
/*
class: 1265094477
MySample is loaded by: sun.misc.Launcher$AppClassLoader@135fbaa4
MyCat is loaded by: sun.misc.Launcher$AppClassLoader@135fbaa4
*/
