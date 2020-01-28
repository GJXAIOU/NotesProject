package com.gjxaiou.classloader;

public class MyTest18_1 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        loader1.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout/");

        Class<?> clazz = loader1.loadClass("zy.jvm.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        System.out.println("class loader: " + clazz.getClassLoader());

// 将zy/jvm/classloader/MyTest1.class文件拷贝到
// /Library/Java/JavaVirtualMachines/jdk1.8.0_112.jdk/Contents/Home/jre/classes 后运行
// 运行结果   类由启动类加载器加载
        /*
class: 1265094477
class loader: null
         */
    }
}
