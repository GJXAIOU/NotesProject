package com.gjxaiou.jdk8.defaultmethod;


public class MyClass extends MyInterface1Impl implements MyInterface2 {

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }
}

class MyClass1 implements MyInterface1, MyInterface2 {
    @Override
    public void myMethod() {
        // 重写该方法
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();
        myClass.myMethod();
    }
}
