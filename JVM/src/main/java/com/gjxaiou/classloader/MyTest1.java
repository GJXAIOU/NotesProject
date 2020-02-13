package com.gjxaiou.classloader;

/**
 * 对于静态字段来说，只有直接定义了该字段的类才会被初始化
 * 当一个类在初始化时，要求其父类全部都已经初始化完毕
 * -XX:+TraceClassLoading，用于追踪运行当前类的加载信息并打印出来（在类的 VM option 中进行配置）
 * <p>
 * JVM 参数的格式：
 * -XX:+<option>，表示开启 option 选项
 * -XX:-<option>，表示关闭 option 选项
 * -XX:<option>=value，表示将 option 的值设置为 value
 */
public class MyTest1 {
    public static void main(String[] args) {
        // 下面语句输出：MyParent static block、hello world
        // 这里的 str 是父类进行定义的，这里主动使用了父类，但是没有主动使用子类，因此子类没有被初始化，最终不会执行子类中的静态代码块；
        System.out.println(MyChild1.str);
        System.out.println("-------");
        // 下面语句输出：MyParent static block、MyChild static block、welcome
        // 因为 str2
        //是子类定义的，这里调用这句话就是对子类的主动调用，所以子类的静态代码块一定会执行，同时主动使用的时候，初始化子类的同时也会初始化父类；（初始化父类的子类，本质上对父类也是主动调用，而子类调用子类的静态变量，也是主动使用。）
        System.out.println(MyChild1.str2);
    }
}

class MyParent1 {
    public static String str = "hello world";

    public static void myPrentMethod() {
        System.out.println("MyParent1 static myPrentMethod");
    }

    static {
        System.out.println("MyParent1 static block");
    }
}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";

    static {
        System.out.println("MyChild1 static block");
    }
}