package com.gjxaiou.class10;


import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class MyTest26{
    public static void main(String[] args){

        // 一旦加入下面此行，将使用ExtClassLoader去加载Driver.class， ExtClassLoader不会去加载classpath，因此无法找到MySql的相关驱动。
        // Thread.getCurrentThread().setContextClassLoader(MyTest26.class.getClassLoader().parent());

        // ServiceLoader服务提供者，加载实现的服务
        ServiceLoader<Driver> loader= ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator=loader.iterator();
        while(iterator.hasNext()){
            Driver driver = iterator.next();
            System.out.println("driver:"+ driver.getClass() + ",loader"+ driver.getClass().getClassLoader());
        }
        System.out.println("当前上下文加载器"
                +Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的加载器"
                +ServiceLoader.class.getClassLoader());
    }
}
/**
 * driver:class com.mysql.cj.jdbc.Driver,loadersun.misc.Launcher$AppClassLoader@18b4aac2
 * // 因为上下文加载器没有设置，所以默认为 AppClassLoader
 * 当前上下文加载器sun.misc.Launcher$AppClassLoader@18b4aac2
 * // 因为 ServiceLoader 位于 java.util 包中，是 Java 核心库，位于 rt.jar 中，所有使用启动类加载器
 * ServiceLoader的加载器null
 */