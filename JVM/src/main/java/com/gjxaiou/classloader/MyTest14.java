package com.gjxaiou.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {
    public static void main(String[] args) throws IOException {
        ClassLoader classLoader =
                Thread.currentThread().getContextClassLoader();
        // 获取当前线程的上下文类加载器，它会获取启动这个应用的类加载器

//        System.out.println(classLoader);
        // 运行结果：sun.misc.Launcher$AppClassLoader@18b4aac2

        String resourceName = "com/gjxaiou/classloader/MyTest14.class";
        // getResource() 根据资源名参数，找出所有的可以被字节码以某种方式访问到的代码资源，与代码位置无关
        Enumeration<URL> urls = classLoader.getResources(resourceName);

        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }

        Class<?> clazz = MyTest14.class;
        System.out.println(clazz.getClassLoader());
        clazz = String.class;
        System.out.println(clazz.getClassLoader());
        /*运行结果：   String属于由bootstrapClassLoader加载的类
        null
         */
    }

}
