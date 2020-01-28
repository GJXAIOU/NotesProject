package com.gjxaiou.classloader;

/**
 * 类继承关系 与 初始化顺序和类加载顺序的 联系
 */
class Parent {
    static int a = 3;
    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent {
    static int b = 4;
    static {
        System.out.println("Child static block");
    }
}

public class MyTest9 {
    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        System.out.println(Child.b);
    }
}
        /*
运行结果：类的初始化顺序确定类要被直接引用后，要先初始化父类，先加载父类
         */
