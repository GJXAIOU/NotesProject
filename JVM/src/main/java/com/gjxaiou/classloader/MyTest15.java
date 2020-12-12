package com.gjxaiou.classloader;

/**
 * 下面说明见：ClassLoader.java 的 doc 文档中
 * 对于数组，它对应的class对象不是由类加载器加载，而是由JVM在运行期动态的创建。然而对于数组类的类加载器来说，它返回的类加载器和数组内元素类型的类加载器是一样的（就是相当于
 * 数组和数组中元素调用 .getClassLoader() 返回值一样）。如果数组类元素是原生类，那么数组是没有类加载器的。
 */
public class MyTest15 {
    public static void main(String[] args) {
        String[] strings = new String[2];
        // 运行结果： class [Ljava.lang.String;
        System.out.println(strings.getClass());
        // 运行结果：null， 因为 String 是由根类加载器(启动类加载器)加载的，所以返回的是null
        System.out.println(strings.getClass().getClassLoader());

        System.out.println("----------");

        MyTest15[] myTest15s = new MyTest15[2];
        // 运行结果：sun.misc.Launcher$AppClassLoader@135fbaa4
        // MyTest15是由AppClassLoader加载的，所以MyTest15类型元素组成的数组的getClassLoader()返回元素的getClassLoader()的返回值
        System.out.println(myTest15s.getClass().getClassLoader());

        int[] ints = new int[2];
        // 运行结果：null    因为 primitive 类型即原生数组，所以它组成的数组没有类加载器，和上面 null 不同
        System.out.println(ints.getClass().getClassLoader());
    }
}
