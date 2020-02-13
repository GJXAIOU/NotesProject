package com.gjxaiou.classloader;

/**
 * 注意：这里指的是将常量存到 MyTest2 的常量池中，之后 MyTest2 和 MyParent2 就没有任何关系了。甚至我们可以将 MyParent2 的 class
 * 文件删除（编译完之后），程序还可以执行。
 * 助记符：反编译之后可以看到
 * <p>
 * 助记符 ldc：表示将 int、float 或者 String 类型的常量值从常量池中推送至栈顶
 * 助记符 bipush：表示将单字节（-128-127）的常量值推送到栈顶
 * 助记符 sipush：表示将一个短整型值（-32768-32369）推送至栈顶
 * 助记符 iconst_1：表示将 int 型的 1 推送至栈顶（iconst_m1 到iconst_5(对应于 -1 到 5)值，6 之后使用 bipush）
 */
public class MyTest2 {
    public static void main(String[] args) {
        // 输出：MyParent static block、 hello world
        System.out.println(MyParent2.str1);
        // 输出：hello world
        System.out.println(MyParent2.str);
        System.out.println(MyParent2.s);
        System.out.println(MyParent2.i);
        System.out.println(MyParent2.j);
    }
}

// 因为先编译后加载，所有该类并没有被加载
class MyParent2 {
    public static String str1 = "hello world";
    public static final String str = "hello world";
    public static final short s = 7;
    public static final int i = 129;
    public static final int j = 1;

    static {
        System.out.println("MyParent static block");
    }
}

