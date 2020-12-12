package com.gjxaiou.classloader;

import com.sun.crypto.provider.AESKeyGenerator;

public class MyTest19 {
    public static void main(String[] args) {
        // AESKeyGenerator 类位于扩展类加载器所加载类的路径中
        AESKeyGenerator aesKeyGenerator = new AESKeyGenerator();

        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        System.out.println(MyTest19.class.getClassLoader());
        // 运行结果
        /*
sun.misc.Launcher$ExtClassLoader@266474c2
sun.misc.Launcher$AppClassLoader@135fbaa4
         */

        // Terminal 下运行结果。
        // 将扩展类加载器所加载类的路径改为当前路径，但当前路径下不存在 AESKeyGenerator 的类文件，所以报错
        /*
zhangyandeMBP:classes zhangyan$ java -Djava.ext.dirs=./ zy.jvm.classloader.MyTest19
Exception in thread "main" java.lang.NoClassDefFoundError: com/sun/crypto/provider/AESKeyGenerator
        at zy.jvm.classloader.MyTest19.main(MyTest19.java:7)
Caused by: java.lang.ClassNotFoundException: com.sun.crypto.provider.AESKeyGenerator
        at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
        at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:331)
        at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
        ... 1 more
         */
    }
}
