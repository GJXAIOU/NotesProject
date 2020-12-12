package com.gjxaiou.classloader;

/**
 * 创建自定义加载器，继承ClassLoader
 */
public class MyTest17 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
// 返回的 class 对象就是 MySample 类对应的 Class 对象，下面可以通过反射创建 MySample 的一个实例
        Class<?> clazz = loader1.loadClass("com.gjxaiou.classloader.MySample");
        System.out.println("class: " + clazz.hashCode());
        // 如果注释掉该行，那么并不会实例化MySample对象，即MySample构造方法不会被调用
        // 因此不会实例化MyCat对象，即没有对MyCat主动使用，这里就不会加载MyCat Class
        //如果注释掉下面该行，就并不会实例化 MySample 对象，不会加载 MyCat（可能预先加载，不一定，通过VM options 可以看到这里是加载了）
        // 因为 newInstance（） 中没有参数，因此会调用 MySample 中的无参构造方法
        //加载和实例化了MySample和MyCat
        System.out.println("----------------");
        Object object = clazz.newInstance();
        System.out.println("----------------");

    }
}
// 运行结果   类由系统类加载器加载
/*
class: 21685669
MySample is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
MyCat is loaded by: sun.misc.Launcher$AppClassLoader@18b4aac2
*/
