package com.gjxaiou.class10;

/**
 java.lang.String是由根加载器加载，在rt.jar包下
 */
public class MyTest7{
    public static void main(String[] args) throws ClassNotFoundException {
            // 加载之前需要获取 class 对象
            Class<?> clazz=Class.forName("java.lang.String");
            // 返回针对该类的类加载器（就是实际加载该类的加载器），其中 null 表示启动类（根类）的加载器
            System.out.println(clazz.getClassLoader());

            Class<?> clazz2=Class.forName("com.gjxaiou.class10.C");
            System.out.println(clazz2.getClassLoader());
    }
}
class C{
}

/** output:
 * null
 * sun.misc.Launcher$AppClassLoader@18b4aac2 :其中AppClassLoader:系统应用类加载器($前为外部类，后为内部类)
 */