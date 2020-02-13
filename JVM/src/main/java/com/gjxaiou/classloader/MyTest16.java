package com.gjxaiou.classloader;

import java.io.*;

/**
 * 自定义类加载器
 * 1. 需要定义一个 ClassLoader 的名字。通过构造函数 MyTest16(String classLoaderName)
 * 2. 可以给这个类加载器指定一个父加载器。通过构造函数 MyTest16(ClassLoader parent, String classLoaderName)
 * 3. 实现 findClass 方法，通过类名可以加载到这个类。通过 loadClassData(String name) 实现
 */
public class MyTest16 extends ClassLoader {

    private String classLoaderName;
    // 从哪里进行加载，如果没有指定就是从项目下
    private String path;
    // 指定类的后缀名
    private final String fileExtension = ".class";

    public MyTest16(String classLoaderName) {
        // 使用 getSystemClassLoader() 方法返回的ClassLoader（即系统类加载器）作为parent ClassLoader来创建一个新的ClassLoader
        // 默认会将系统（应用）类加载器当做该类的父加载器
        super();
        this.classLoaderName = classLoaderName;
    }

    /**
     * 使用指定的parent类加载器作为委托的类加载器去创建一个新的类加载器。
     */
    public MyTest16(ClassLoader parent, String classLoaderName) {
        // 显式指定该类加载器的父加载器
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public MyTest16(ClassLoader parent) {
        super(parent);
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 在使用 MyTest16(String) 这一构造函数时 findClass 方法根本没有执行，className的类由系统类加载器（父加载器）加载
     * 根据 className 来寻找该类，该类在检查完父类加载器之后自动被 java.lang.ClassLoader 的 loadClass 方法调用，而这里我们没有重写
     * loadClass 方法，因此会自动调用
     */
    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + this.classLoaderName);
        // 因为传入的格式为： com.a.b,需要转换为路径格式：com/a/b（以 Windows 为例）
        className = className.replace(".", File.separator);

        byte[] data = new byte[0];
        data = loadClassData(className);
        // defineClass方法 将 字节数组data 转换为Class实例，在使用这个Class实例之前，它必须是已被解析的
        return defineClass(className, data, 0, data.length);
    }

    /**
     * 加载类数据，自定义的私有方法，由 findClass 方法调用
     * 要读取类文件的字节数组
     * 根据类的名字，将类的二进制数组数组加载出来(将它的文件找到，然后以输入输出流的方式返回字节数组，该字节数组就是从文件中读取出的 class 文件的二进制信息)
     */
    private byte[] loadClassData(String className) {
        InputStream is = null;
        // 存储返回值
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        // 将类名中包的分隔符变为当前文件系统的分隔符
        className = className.replace(".", "/");

        try {
            is = new FileInputStream(new File(this.path + className + this.fileExtension));
            baos = new ByteArrayOutputStream();

            int ch = 0;
            // 每次从输入流读取一个字节
            while (-1 != (ch = is.read())) {
                baos.write(ch);
            }
            // 将字节数组输出流的内容直接转换为字节数组
            data = baos.toByteArray();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return data;
    }

    public static void test(ClassLoader classLoader) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        // loadClass方法，加载指定二进制名字的类，调用loadClass(String className, false)方法去加载类
        // loadClass(String className, false)方法默认实现搜索类的顺序如下
        // 1.  Invoke findLoadedClass(String) to check if the class has already been loaded.
        // 2. Invoke the {@link #loadClass(String) <tt>loadClass</tt>} method
        //  on the parent class loader.  If the parent is <tt>null</tt> the class
        //  loader built-in to the virtual machine is used, instead.
        // 3. Invoke the {@link #findClass(String)} method to find the class.

        // loadClass 底层会调用上面的 findClass 和 loadClassData
        Class<?> clazz = classLoader.loadClass("com.gjxaiou.classloader.MyTest1");
        //loadClass是父类方法，在方法内部调用findClass
        System.out.println(clazz.hashCode());
        // 通过 class 对象获取相应想要创建的实例
        Object object = clazz.newInstance();

        System.out.println(object);
        System.out.println(clazz.getClassLoader().toString());
        // 结果是
        // zy.jvm.classloader.MyTest1@2503dbd3
        // sun.misc.Launcher$AppClassLoader@135fbaa4
        // 系统类加载器是 MyTest1的 定义类加载器，而MyTest16和系统类加载器是MyTest1的 初始类加载器
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException,
            ClassNotFoundException, InterruptedException {
        // 创建一个类加载器 loader1，其双亲类加载器是 系统类加载器
        //父亲是系统类加载器，根据父类委托机制，MyTest1由系统类加载器加载了，并不是自己定义的加载器加载的，因为上面有一个 super（） 方法
        MyTest16 loader1 = new MyTest16("loader1");
//        test(loader1);
        // 使类加载器loader1在设置的路径上去寻找相关的类，但这个路径是classpath，所以系统类加载器会直接加载这个路径里的类
//        loader1.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/out
//       /production/classes");
        // 将工程中的zy.jvm.classloader.MyTest1的类文件移动到工程的classpath路径以外，就会由MyTest16加载MyTest1
        loader1.setPath("/out/production/JVM/com/gjxaiou/classloader/");

        Class<?> clazz = loader1.loadClass("com.gjxaiou.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        Object object = clazz.newInstance();

        System.out.println(object);
        System.out.println(clazz.getClassLoader().toString());

        System.out.println("==================================");

        // 将工程中的zy.jvm.classloader.MyTest1的类文件移动到工程的classpath路径以外，
        // 就会由MyTest16的不同实例分别加载MyTest1类，这是因为MyTest16的不同实例构成了不同的类命名空间
        // 然而，如果classpath中存在zy.jvm.classloader.MyTest1的类文件，
        // 那么loader1加载时会由其父类加载器(系统类加载器)加载，
        // loader2加载时父类加载器会先查找MyTest1有没有被加载，被加载过就不会再次加载
        MyTest16 loader2 = new MyTest16("loader2");
        loader2.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout/");

        Class<?> clazz2 = loader2.loadClass("com.gjxaiou.classloader.MyTest1");
        System.out.println("class: " + clazz2.hashCode()); //用Class实例的hashCode以标识是否为同一个实例
        Object object2 = clazz2.newInstance();

        System.out.println(object2);
        System.out.println(clazz2.getClassLoader().toString());

        System.out.println("==================================");

        MyTest16 loader3 = new MyTest16(loader1, "loader3");
        loader2.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout/");

        Class<?> clazz3 = loader3.loadClass("com.gjxaiou.classloader.MyTest1");
        System.out.println("class: " + clazz3.hashCode()); // 类由loader3的父加载器loader1加载
        Object object3 = clazz3.newInstance();

        System.out.println(object3);
        System.out.println(clazz3.getClassLoader().toString());

        System.out.println("==================================");
        /*
        要查看类的卸载过程，要加入 -XX:+TraceClassUnloading，并进行一次垃圾回收
        不过在 mac 上运行，没有效果
         */
        loader1 = null;
        clazz = null;
        object = null;

        System.gc();

        Thread.sleep(100000);  // 暂停继续运行，方便使用jvisualvm查看内存使用情况

        loader1 = new MyTest16("loader1");
        loader1.setPath("/Users/zhangyan/Documents/Learning/SelfCodes/jvm_lecture/cpout/");

        clazz = loader1.loadClass("com.gjxaiou.classloader.MyTest1");
        System.out.println("class: " + clazz.hashCode());
        object = clazz.newInstance();

        System.out.println(object);
        System.out.println(clazz.getClassLoader().toString());

    }
}
