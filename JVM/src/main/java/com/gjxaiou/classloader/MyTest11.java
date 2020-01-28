package com.gjxaiou.classloader;

class Parent3 {
    static int a = 3;

    static {
        System.out.println("Parent3 static block");
    }

    static void doSomething() {
        System.out.println("do something");
    }
}

class Child3 extends Parent3 {
    static {
        System.out.println("Child3 static block");
    }
}

public class MyTest11 {
    public static void main(String[] args) {

        System.out.println(Child3.a);
        System.out.println("-----------------");
        Child3.doSomething();
    }
}
/*
运行结果：没有主动使用Child3，因为a定义在Parent3中，通过子类引用父类定义的内容，只会直接使用父类，而不会直接使用子类
Parent3 static block
3
-----------------
do something
*/
