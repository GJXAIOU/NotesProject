package com.gjxaiou.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {
    public static void main(String[] args) throws IOException {
        // 获取当前执行线程的上下文加载器,它会获取启动这个应用的类加载器
        ClassLoader classLoader =
                Thread.currentThread().getContextClassLoader();

        // 运行结果：sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);

        String resourceName = "com/gjxaiou/classloader/MyTest14.class";
        // getResource() 根据资源名参数，找出所有的可以被字节码以某种方式访问到的代码资源，与代码位置无关
        Enumeration<URL> urls = classLoader.getResources(resourceName);

        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            // 输出：file:/E:/Program/Java/Project/JVM/target/classes/com/gjxaiou/classloader.MyTest14.class
            System.out.println(url);
        }

        Class<?> clazz = MyTest14.class;
        // 输出：sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(clazz.getClassLoader());
        clazz = String.class;
        //  null
        System.out.println(clazz.getClassLoader());
    }
}
