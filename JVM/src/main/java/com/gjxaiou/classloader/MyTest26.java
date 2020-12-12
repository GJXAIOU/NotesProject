package com.gjxaiou.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class MyTest26 {

    public static void main(String[] args) {
        // 如果将 当前线程的上下文类加载器设置为 扩展类加载器 的话，ServiceLoader会用扩展类加载器加载
        // MySQL提供的 java.sql.Driver 的实现类，因为扩展类加载器不能加载到ClassPath里的jar，所以MySQL的实现类不会被加载
        //Thread.currentThread().setContextClassLoader(MyTest26.class.getClassLoader().getParent());

        // ServiceLoader 读取 mysql-connector-java-5.1.34.jar包下的 META-INF/services/java.sql.Driver
        // 而这个文件里的内容就是 com.mysql.jdbc.Driver
        //                      com.mysql.fabric.jdbc.FabricMySQLDriver
        // 这个文件的命名方式和内容，是 服务实现类根据的标准要求来实现的
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();

        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            // 打印出 ServiceLoader加载的 实现了Driver接口的类信息，以及这个实现了Driver接口的类的类加载器
            System.out.println("driver: " + driver.getClass() + ", loader: " + driver.getClass().getClassLoader());
        }
        // 上面代码的运行结果
        /*
driver: class com.mysql.jdbc.Driver, loader: sun.misc.Launcher$AppClassLoader@135fbaa4
driver: class com.mysql.fabric.jdbc.FabricMySQLDriver, loader: sun.misc.Launcher$AppClassLoader@135fbaa4
         */

        System.out.println("当前线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
        // 上面代码的运行结果
        /*
当前线程上下文类加载器：sun.misc.Launcher$AppClassLoader@135fbaa4
ServiceLoader的类加载器：null
         */
        // ServiceLoader位于rt.jar，所以由启动器类加载器加载。默认的当前线程上下文类加载器是 AppClassLoader
    }
}
