package com.gjxaiou.classloader;

import java.lang.reflect.Method;
/*
将 MyPerson 类文件从 /Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/out/production/classes
移动至 /Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout 后，再运行
 */
public class MyTest21 {
    public static void main(String[] args) throws Exception {
        MyTest16 loader1 = new MyTest16("loader1");
        MyTest16 loader2 = new MyTest16("loader2");

        loader1.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout/");
        loader2.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout/");

        Class<?> clazz1 = loader1.loadClass("zy.jvm.classloader.MyPerson");
        Class<?> clazz2 = loader2.loadClass("zy.jvm.classloader.MyPerson");

        System.out.println(clazz1==clazz2);
        // 运行结果为
        /*
findClass invoked: zy.jvm.classloader.MyPerson
class loader name: loader1
findClass invoked: zy.jvm.classloader.MyPerson
class loader name: loader2
false
         */
        // 因为 MyPerson 类是由loader1 和 loader2 分别加载的，loader1和loader2从类加载的双亲委托机制来看没有任何关系，
        // 它们会在JVM内存中形成两个命名空间

        Object object1 = clazz1.newInstance();
        Object object2 = clazz2.newInstance();

        Method method = clazz1.getMethod("setMyPerson", Object.class);
        method.invoke(object1, object2);
        // MyPerson第6行是 Object 类型参数，MyTest21第34行的第二个参数是 Object.class
        // 运行到上一行。因为loader1和loader2的命名空间互不可见，所以它们加载的同名类是互不可见的
        /*
Exception in thread "main" java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at zy.jvm.classloader.MyTest21.main(MyTest21.java:34)
Caused by: java.lang.ClassCastException: zy.jvm.classloader.MyPerson cannot be cast to zy.jvm.classloader.MyPerson
	at zy.jvm.classloader.MyPerson.setMyPerson(MyPerson.java:7)
	... 5 more
         */
        // MyPerson第6行由 Object 类型参数 改为 MyPerson，MyTest21第34行的第二个参数由 Object.class 改为 clazz1
        // 运行到上一行。
/*
Exception in thread "main" java.lang.IllegalArgumentException: argument type mismatch
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at zy.jvm.classloader.MyTest21.main(MyTest21.java:35)
 */
        // MyPerson第6行由 Object 类型参数 改为 MyPerson，MyTest21第34行的第二个参数由 Object.class 改为 clazz2
        // 运行到上一行。
/*
Exception in thread "main" java.lang.NoSuchMethodException: zy.jvm.classloader.MyPerson.setMyPerson(zy.jvm.classloader.MyPerson)
	at java.lang.Class.getMethod(Class.java:1786)
	at zy.jvm.classloader.MyTest21.main(MyTest21.java:34)
 */
    }
}
