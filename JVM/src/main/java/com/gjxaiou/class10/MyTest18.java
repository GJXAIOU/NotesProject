package com.gjxaiou.class10;

public class MyTest18{
    public static void main(String[] args){
        //根加载器路径
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println("------------------");
        //扩展类加载器路径
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println("------------------");
        //应用类加载器路径
        System.out.println(System.getProperty("java.class.path"));
    }
}

/** output:
 *E:\Program\Java\JDK1.8\jre\lib\resources.jar;
 * E:\Program\Java\JDK1.8\jre\lib\rt.jar;        ☆☆☆
 * E:\Program\Java\JDK1.8\jre\lib\sunrsasign.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jsse.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jce.jar;
 * E:\Program\Java\JDK1.8\jre\lib\charsets.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jfr.jar;
 * E:\Program\Java\JDK1.8\jre\classes
 * ------------------
 * E:\Program\Java\JDK1.8\jre\lib\ext;  ☆☆☆
 * C:\WINDOWS\Sun\Java\lib\ext
 * ------------------
 * E:\Program\Java\JDK1.8\jre\lib\charsets.jar;
 * E:\Program\Java\JDK1.8\jre\lib\deploy.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\access-bridge-64.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\cldrdata.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\dnsns.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\jaccess.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\jfxrt.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\localedata.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\nashorn.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\sunec.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\sunjce_provider.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\sunmscapi.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\sunpkcs11.jar;
 * E:\Program\Java\JDK1.8\jre\lib\ext\zipfs.jar;
 * E:\Program\Java\JDK1.8\jre\lib\javaws.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jce.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jfr.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jfxswt.jar;
 * E:\Program\Java\JDK1.8\jre\lib\jsse.jar;
 * E:\Program\Java\JDK1.8\jre\lib\management-agent.jar;
 * E:\Program\Java\JDK1.8\jre\lib\plugin.jar;
 * E:\Program\Java\JDK1.8\jre\lib\resources.jar;
 * E:\Program\Java\JDK1.8\jre\lib\rt.jar;
 * E:\Program\Java\JVM\DemoByMyself\out\production\DemoByMyself; ☆☆☆
 * D:\JetBrains\IntelliJ IDEA 2019.2.4\lib\idea_rt.jar
 */
