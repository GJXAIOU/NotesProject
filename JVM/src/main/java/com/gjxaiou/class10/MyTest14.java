package com.gjxaiou.class10;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

public class MyTest14 {
    public static void main(String[] args) throws IOException {
        // 获取当前执行线程的上下文加载器
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(loader);

        String resourceName = "com/gjxaiou/class10/MyTest14.class";
        Enumeration<URL> urls = loader.getResources(resourceName);
        while (urls.hasMoreElements()) {
            URL url = urls.nextElement();
            System.out.println(url);
        }
    }
}

/**
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 * file:/E:/Program/Java/JVM/DemoByMyself/out/production/DemoByMyself/com/gjxaiou/class10/MyTest14.class
 */
