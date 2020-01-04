package com.gjxaiou.class10;

/**
 对于数组，它对应的class对象不是由类加载器加载，而是由JVM在运行期动态的创建。然而对于数组类的类加载器来说，它返回的类加载器和数组内元素类型的类加载器是一样的（就是相当于 数组和数组中元素调用 .getClassLoader() 返回值一样）。如果数组类元素是原生类，那么数组是没有类加载器的。
 */
public class MyTest15{
    public static void main(String[] args){
        String[] strings=new String[2];
        System.out.println(strings.getClass());
        System.out.println("-------");
        System.out.println(strings.getClass().getClassLoader());
        System.out.println("-------");
        MyTest15[] mytest15=new MyTest15[2];
        System.out.println(mytest15.getClass().getClassLoader());
        System.out.println("-------");
        int[] arr=new int[2];
        System.out.println(arr.getClass().getClassLoader());
    }
}

/**
 * class [Ljava.lang.String;
 * -------
 * null：这里的 null 表示根类加载器
 * -------
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * -------
 * null ：这里的 null 和上面的 null 不一样，这里是原生数组，因此没有类加载器
 */
