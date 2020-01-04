package com.gjxaiou.class10;

/**
 创建自定义加载器，继承ClassLoader
 */
public class MyTest17 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException,
            InstantiationException {
        MyTest16 loader1=new MyTest16("loader1");
        // 返回的 class 对象就是 MySample 类对应的 Class 对象，下面可以通过反射创建 MySample 的一个实例
        Class<?> clazz=loader1.loadClass("com.gjxaiou.class10.MySample");
        System.out.println(clazz.hashCode());
        //如果注释掉下面该行，就并不会实例化 MySample 对象，不会加载 MyCat（可能预先加载，不一定，通过VM options 可以看到这里是加载了）
        // 因为 newInstance（） 中没有参数，因此会调用 MySample 中的无参构造方法
        System.out.println("----------------");
        Object  object=clazz.newInstance(); //加载和实例化了MySample和MyCat
        System.out.println("---------------");
    }
}
/**
 *460141958
 * MySample is loaded...sun.misc.Launcher$AppClassLoader@18b4aac2
 * MyCat is loaded...sun.misc.Launcher$AppClassLoader@18b4aac2
 */
