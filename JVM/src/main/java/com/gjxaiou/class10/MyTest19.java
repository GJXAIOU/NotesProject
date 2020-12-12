package com.gjxaiou.class10;

import com.sun.crypto.provider.AESKeyGenerator;

/**
 各加载器的路径是可以修改的，修改后会导致运行失败，ClassNotFoundExeception
 使用命令： java -Djava.ext.dirs=./ com.gjxaiou.class10.MyTest19
 上面命令是将拓展类加载器的路径修改为当前目录，然后执行该类（因为当前目录不存在 AESKeyGenerator 类，所有报错）
 */
public class MyTest19{
    public static void main(String[] args){
        AESKeyGenerator aesKeyGenerator=new AESKeyGenerator();
        //输出扩展类加载器
        System.out.println(aesKeyGenerator.getClass().getClassLoader());
        //输出应用类加载器
        System.out.println(MyTest19.class.getClassLoader());
    }
}

/** output
 * sun.misc.Launcher$ExtClassLoader@4b67cf4d
 * sun.misc.Launcher$AppClassLoader@18b4aac2
 */
