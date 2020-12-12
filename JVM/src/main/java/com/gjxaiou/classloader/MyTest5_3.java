package com.gjxaiou.classloader;

public class MyTest5_3 {
    public static void main(String[] args) {
        // 这里主动使用了 MyChild5，因此其会被初始化，但是 MyParent5 中代码没有执行，因此没有初始化它的接口
        System.out.println(MyChild5_3.b);
    }
}

interface MyParent5_3 {
    public static Thread thread = new Thread() {
        {
            System.out.println(" MParent5 invoke");
        }
    };
}

class MyChild5_3 implements MyParent5_3 {
    public static int b = 6;
}