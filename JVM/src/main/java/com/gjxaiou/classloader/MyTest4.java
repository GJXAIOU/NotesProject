package com.gjxaiou.classloader;

/**
 * 助记符：anewarray：表示创建一个引用类型（如类、接口）的数组，并将其引用值压入栈顶
 * 助记符：newarray：表示创建一个指定原始类型（如int boolean float double char）的数组，并将其引用值压入栈顶
 */
public class MyTest4 {
    public static void main(String[] args) {
        // 创建类的实例，属于主动使用，会导致类的初始化
        // 当创建数组类型的实例，并不表示对数组中的元素的主动使用，而仅仅表示创建了这个数组的实例而已，数组 new 出来的实例类型有由 JVM 在运行期动态生成的。
        // 具体的类型以一维原始类型为例(一维是 [,二维是 [[)： int -> [I, char ->[C, boolean -> [Z, short -> [S, byte ->B
        MyParent4 myParent4 = new MyParent4();

        // 情况一：针对原生类型的数组
        int[] i = new int[1];
        // 输出: class [ I
        System.out.println(i.getClass());
        // 输出: class java.lang.Object
        System.out.println(i.getClass().getSuperclass());

        // 情况二：针对引用类型数组
        // 不是主动使用
        MyParent4[] myParent4s = new MyParent4[1];
        // 输出： class [Lcom.gjxaiou.classloader.MyParent4;
        System.out.println(myParent4s.getClass());
        // 输出： class java.lang.Object
        System.out.println(myParent4s.getClass().getSuperclass());
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent static block");
    }
}
