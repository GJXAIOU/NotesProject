package com.gjxaiou.classloader;

/**
 * @author GJX
 * 第19课创建，用来演示在一个类中创建另一个类的实例时类加载的情况
 */
public class MyCat {

    public MyCat() {
        // 把加载 MyCat（）类的 class 对象打印出来
        // this.getClass() 获取调用类所对应的唯一的 Class 对象
        // 因为类加载器加载的是类对应的 Class 对象，因此 Class 对象中有 getClassLoader() 方法
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());
    }
}
