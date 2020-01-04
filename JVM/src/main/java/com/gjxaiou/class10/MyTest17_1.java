package com.gjxaiou.class10;

public class MyTest17_1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {

        MyTest16 loader1=new MyTest16("loader1");
        loader1.setPath("C:/Users/gjx16/Desktop/");
        Class<?> clazz=loader1.loadClass("com.gjxaiou.class10.MySample");
        System.out.println(clazz.hashCode());
        Object  object=clazz.newInstance();
    }
}