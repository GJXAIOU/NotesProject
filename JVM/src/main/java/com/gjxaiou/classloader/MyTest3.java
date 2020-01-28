package com.gjxaiou.classloader;

import java.util.UUID;

/**
 * 当一个常量的值并非编译期间可以确定的（即运行期间才能确定），那么其值就不会被放到调用类的常量池中，
 * 这时在程序运行时，会导致主动使用这个常量所在的类，显然会导致这个类被初始化
 *
 * 所以在运行 MyTest3的main时，必须有 MyParent3.class 存在
 * 本示例中，MyParent3.str的值不能在编译阶段确定，所以MyParent3会被主动使用
 */
public class MyTest3 {
    public static void main(String[] args) {
        System.out.println(MyParent3.str);
        // 运行结果
//        MyParent3 static code
//        8c5ae939-56a7-4f1c-8cba-2c23e8234f48
    }
}

class MyParent3 {
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static code");
    }
}