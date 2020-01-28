package com.gjxaiou.classloader;

/**
 * 对于数组实例来说，其类型是由JVM在运行期动态生成的，表示为 [com.gjxaiou.classloader.MyParent4 这种形式。
 * 动态生成的类型，其父类型就是Object。
 * 对于数组来说，JavaDoc经常将构成数组的元素称为Component，实际上就是数组降低一个维度后的类型。
 * 数组的声明不会导致主动使用元素类的初始化。
 * @author GJX
 */
public class MyTest4 {
    public static void main(String[] args) {
        /*
        // 首次主动使用 MyParent4，导致MyParent4类的初始化
        MyParent4 myParent4 = new MyParent4();
        System.out.println("================");
        // 非首次主动使用 MyParent4，不会导致MyParent4类的初始化
        MyParent4 myParent5 = new MyParent4();
        // 运行结果
//MyParent4 static block
//================
         */
        // 没有导致 MyParent4 的主动使用，MyParent4不会被初始化
        MyParent4[] myParent4s = new MyParent4[1];
        System.out.println(myParent4s.getClass());
        System.out.println(myParent4s.getClass().getSuperclass());
        //运行结果 class [com.gjxaiou.classloader.MyParent4;和class java.lang.Object，JVM在运行期创建出[Lzy.jvm
        //.classloader.MyParent4这个类型
        MyParent4[][] myParent4s1 = new MyParent4[1][1];
        System.out.println(myParent4s1.getClass());
        System.out.println(myParent4s1.getClass().getSuperclass());
        //运行结果 class [[com.gjxaiou.classloader.MyParent4;和class java.lang.Object，JVM在运行期创建出[[Lzy.jvm
        //.classloader.MyParent4这个类型

        System.out.println("============================");

        int[] ints = new int[1];
        System.out.println(ints.getClass());
        System.out.println(ints.getClass().getSuperclass());
        // 运行结果
//        class [I
//        class java.lang.Object
        // char数组的类型是 [C，boolean数组的类型是 [Z，short数组的类型是 [S，byte数组的类型是 [B
    }
}

class MyParent4 {
    static {
        System.out.println("MyParent4 static block");
    }
}