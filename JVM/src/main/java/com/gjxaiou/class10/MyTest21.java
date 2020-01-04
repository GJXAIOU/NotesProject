package com.gjxaiou.class10;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
//删掉 classpath 下的 MyPerson 类，桌面上当然有
public class MyTest21{
    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");
        loader1.setPath("C:/Users/gjx16/Desktop/");
        loader2.setPath("C:/Users/gjx16/Desktop/");
        // 这里是 loader1 真正加载了该类，loader1 就是定义类加载器
        Class<?> clazz1 = loader1.loadClass("com.gjxaiou.class10.MyPerson");
        // 这里是 loader2 真正加载了该类，loader2 就是定义类加载器
        Class<?> clazz2 = loader2.loadClass("com.gjxaiou.class10.MyPerson");
        //clazz1和clazz由loader1和loader2加载，从双亲委托机制来看它们之间没有任何关系，两者都会在 JVM 开辟内存空间，加载对应的 class
        // 对象，两者的命名空间完全独立（因为每个类加载器都有自己的命名空间），因此结果为false
        System.out.println(clazz1 == clazz2);

        Object object1=clazz1.newInstance();
        Object object2=clazz2.newInstance();

        Method method=clazz1.getMethod("setMyPerson",Object.class);
        //此处报错，loader1和loader2所处不用的命名空间
        method.invoke(object1,object2);
    }
}
