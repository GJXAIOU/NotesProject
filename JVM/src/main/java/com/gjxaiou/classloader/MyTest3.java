package com.gjxaiou.classloader;

import java.util.UUID;

/**
 * 在运行 MyTest3 的 main 时，必须有 MyParent3.class 存在；
 * 这里因为 MyParent3.str 的值不能在编译阶段确定，所以 MyParent3 会被主动使用，对应的静态代码块执行。
 *
 * @author GJXAIOU
 */
public class MyTest3 {
    public static void main(String[] args) {
        // 下面语句输出结果为：
        //MyParent3 static block
        //82fcced1-a3e3-4c39-b4e8-b8a01b32c478
        System.out.println(MyParent3.str);
    }
}

class MyParent3 {
    public static final String str = UUID.randomUUID().toString();

    static {
        System.out.println("MyParent3 static block");
    }
}