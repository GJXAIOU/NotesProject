package com.gjxaiou.class10;

public class MyTest5 {
    public static void main(String[] args) {
            System.out.println(MyChild5.thread);
        }
}

interface MyParent5 {
    public static Thread thread = new Thread(){
        {
            System.out.println(" MParent5 invoke");
        }
    };
}

interface MyChild5 extends MyParent5 {
    public static Thread thread = new Thread(){
        {
            System.out.println(" MyChild5 invoke");
        }
    };
}