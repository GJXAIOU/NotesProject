package com.gjxaiou.classloader;

/**
 * 类继承关系 与 初始化顺序和类加载顺序的 联系
 */
public class MyTest9 {
    static {
        System.out.println("MyTest9 static block");
    }

    public static void main(String[] args) {
        // 输出：
        // MyTest9 static block
        // Parent static block
        // Child static block
        // 2
        System.out.println(Child.b);
    }
}

class Parent {
    static int a = 1;

    static {
        System.out.println("Parent static block");
    }
}

class Child extends Parent {
    static int b = 2;

    static {
        System.out.println("Child static block");
    }
}