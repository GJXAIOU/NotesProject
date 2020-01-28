package com.gjxaiou.classloader;

/**
 * 用来验证
 * Class objects for array classes are not created by class loaders,
 * but are created automatically as required by the Java runtime.
 * The class loader for an array class, as returned by Class.getClassLoader() is the same as the class loader for its element type; if the element type is a primitive type, then the array class has no class loader.
 * if the element type is a primitive type, then the array class has no  class loader.
 */
public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        System.out.println(strings.getClass());
        //运行结果： class [Ljava.lang.String;
        System.out.println(strings.getClass().getClassLoader());
        //运行结果：null    String是由根类加载器(启动类加载器)加载的，所以它的 getClassLoader() 返回的是null

        System.out.println("----------");

        MyTest15[] myTest15s = new MyTest15[2];
        System.out.println(myTest15s.getClass().getClassLoader());
        //运行结果：sun.misc.Launcher$AppClassLoader@135fbaa4
        // MyTest15是由AppClassLoader加载的，所以MyTest15类型元素组成的数组的getClassLoader()返回元素的getClassLoader()的返回值

        int[] ints = new int[2];
        System.out.println(ints.getClass().getClassLoader());
        //运行结果：null    因为primitive类型，所以它组成的数组没有classloader

    }
}
