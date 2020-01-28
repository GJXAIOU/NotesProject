package com.gjxaiou.classloader;

/**
 * @author GJX
 * 第19课创建，用来演示在一个类中创建另一个类的实例时类加载的情况
 */
public class MyCat {

    public MyCat() {
        System.out.println("MyCat is loaded by: " + this.getClass().getClassLoader());
    }
}
