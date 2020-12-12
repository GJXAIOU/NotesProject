package com.gjxaiou.class10;

// 输出类加载器的层次结构
public class MyTest13 {
    public static void main(String[] args) {
        ClassLoader loader = ClassLoader.getSystemClassLoader();
        System.out.println(loader);
        System.out.println("-----------");
        while (loader != null) {
            loader = loader.getParent();
            System.out.println(loader);
        }
    }
}

/*
sun.misc.Launcher$AppClassLoader@18b4aac2
-----------
sun.misc.Launcher$ExtClassLoader@1b6d3586
null：表示根加载器
 */