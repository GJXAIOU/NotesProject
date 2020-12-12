package com.gjxaiou.class10;

import java.io.*;

/**
 * 创建自定义加载器，都需要继承 ClassLoader
 */
public class MyTest16 extends ClassLoader {
    private String classLoaderName;
    // 从哪里进行加载，如果没有指定就是从项目下
    private String path;
    // 指定类的后缀名
    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        // 默认会将系统（应用）类加载器当做该类的父加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent, String classLoaderName) {
        // 显式指定该类加载器的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }


    public void setPath(String path) {
        this.path = path;
    }

    // 根据 className 来寻找该类，该类在检查完父类加载器之后自动被 loadClass 调用，而这里我们没有重写 loadClass 方法，因此会自动调用
    @Override
    protected Class<?> findClass(String className) {
        System.out.println("className =" + className);
        System.out.println("class loader name:" + this.classLoaderName);

        byte[] data = new byte[0];
        try {
            data = this.loadClassData(className);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return this.defineClass(className, data, 0, data.length); //define方法为父类方法
    }

    // 根据类的名字，将类的二进制数组数组加载出来(从磁盘上将它的class文件找到，然后以输入输出流的方式返回字节数组（将 class
    // 文件读取到内存中，放置到一个字节数组中，则该class 信息就位于内存中了），最后通过 defineClass（）方法将该字节数组转换为真正的 class 对象)
    // defineClass() 方法执行之后该 class 对象就生成了，即在 JVM 中客观存在了
    private byte[] loadClassData(String className) throws IOException {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;
        // 因为传入的格式为： com.a.b,需要转换为路径格式：com/a/b（以 Windows 为例）
        className = className.replace(".", File.separator);
        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();
            int ch;
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            data = baos.toByteArray();

        } catch (Exception e) {
        } finally {
            is.close();
            baos.close();
            return data;
        }
    }

    public static void test(ClassLoader classLoader) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        // loadClass 底层会调用上面的 findClass 和 loadClassData
        Class<?> clazz = classLoader.loadClass("com.gjxaiou.class10.MyTest7");
        //loadClass是父类方法，在方法内部调用findClass
        System.out.println("class" + clazz.hashCode());
        // 通过 class 对象获取相应想要创建的实例
        Object object = clazz.newInstance();

        System.out.println(object);
    }


    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException {
//        //父亲是系统类加载器，根据父类委托机制，MyTest1由系统类加载器加载了，并不是自己定义的加载器加载的，因为上面有一个 super（） 方法
//        MyTest16 loader1 = new MyTest16("loader1");
//        test(loader1);
//        /** 只执行 loader1
//         * output:
//         * 460141958
//         * com.gjxaiou.class10.MyTest7@4554617c
//         */
//
        //仍然是系统类加载器进行加载的，因为路径正好是classpath
        MyTest16 loader2 = new MyTest16("loader2");
        loader2.path = "/out/production/DemoByMyself/com/gjxaiou/class10/";
        test(loader2);
        /** 只执行 loader2
         * output:
         * 460141958
         * com.gjxaiou.class10.MyTest7@4554617c
         */
        loader2 = null;
        System.gc();
        loader2 = new MyTest16("loader2");
        loader2.path = "/out/production/DemoByMyself/com/gjxaiou/class10/";
        test(loader2);

        //自定义的类加载器被执行，findClass 方法下的输出被打印。前提是当前 classpath 下不存在 MyTest7
        // .class，MyTest16的父加载器-系统类加载器会尝试从classpath中寻找MyTest7。
        MyTest16 loader3 = new MyTest16("loader3");
        // 在桌面上创建同样上面目录，然后将该类放进去(同时将左边生成的 MyTest7 删除)
        loader3.path = "C:/Users/gjx16/Desktop/";
        test(loader3);

        //与3同时存在，输出两个class的hash不一致，findClass方法下的输出均被打印，原因是类加载器的命名空间问题。（删左边）
        MyTest16 loader4 = new MyTest16("loader4");
        loader4.path = "C:/Users/gjx16/Desktop/";
        test(loader4);

        //将loader3作为父加载器
        MyTest16 loader5 = new MyTest16(loader3, "loader5");
        test(loader5);
    }
}
