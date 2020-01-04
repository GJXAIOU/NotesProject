package com.gjxaiou.class10;

public class MyTest18_1{
    public static void main(String[] args) throws ClassNotFoundException {
        MyTest16 loader1=new MyTest16("loader1");
        loader1.setPath("C:/Users/gjx16/Desktop/");

        //把MyTest7.class文件放入到根类加载器路径中，则由根类加载器加载MyTest7
        Class<?> clazz= loader1.loadClass("com.gjxaiou.class10.MyTest7");

        System.out.println("clazz:"+clazz.hashCode());
        System.out.println("class loader:"+clazz.getClassLoader());

    }
}

/** output:
 * clazz:1627674070
 * class loader:null
 */