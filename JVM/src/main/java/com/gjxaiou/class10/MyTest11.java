package com.gjxaiou.class10;

public class MyTest11 {
    public static void main(String[] args) {
        // 因为 a 是定义在父类中，因此是对于父类的主动使用（静态变量定义在哪（即谁拥有）就是对谁的主动使用）
        System.out.println(Child3.a);
        System.out.println("-------------");
        Child3.doSomething();
    }
}

class Parent3{
    static int a = 3;
    static{
        System.out.println("Parent static block");
    }
    static void doSomething(){
        System.out.println("do something");
    }
}

class Child3 extends Parent3{
    static {
        System.out.println("Child3 static block");
    }
}

/**
 * Parent static block
 * 3
 * -------------
 * do something
 */

