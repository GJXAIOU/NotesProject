package com.gjxaiou.class10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTest20{
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        // 两个类加载器尝试加载同一个 class 对象
        Class<?> clazz1 = loader1.loadClass("com.gjxaiou.class10.MyPerson");
        Class<?> clazz2 = loader2.loadClass("com.gjxaiou.class10.MyPerson");
        //clazz1和clazz均由应用类加载器加载的，第二次不会重新加载，结果为true
        System.out.println(clazz1==clazz2);

        // 分别通过反射创建它们的实例
        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson",Object.class);
        method.invoke(object1,object2);
    }
}