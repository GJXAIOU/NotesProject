package com.gjxaiou.classloader;

/**
 * 类继承关系 与 初始化顺序和类加载顺序的 联系
 */
public class MyTest10 {
    public static void main(String[] args) {
        // 声明一个类的引用不是对这个类的主动使用，这行代码不会导致任何输出
        Parent2 parent2;
        System.out.println("----------------------");

        parent2 = new Parent2();
        System.out.println("----------------------");

        System.out.println(Parent2.a);
        System.out.println("----------------------");

        System.out.println(Child2.b);
    }

    static {
        System.out.println("MyTest10 static block");
    }
}

class Parent2 {
    static int a = 3;

    static {
        System.out.println("Parent2 static block");
    }
}

class Child2 extends Parent2 {
    static int b = 4;

    static {
        System.out.println("Child2 static block");
    }
}


/*
运行结果：类的初始化顺序确定类要被直接引用后，要先初始化父类，先加载父类
MyTest10 static block
----------------------
Parent2 static block
----------------------
3
----------------------
Child2 static block
4
*/
