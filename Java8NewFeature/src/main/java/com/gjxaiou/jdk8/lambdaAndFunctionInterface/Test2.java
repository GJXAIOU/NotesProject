package com.gjxaiou.jdk8.lambdaAndFunctionInterface;

@FunctionalInterface
interface MyInterface {
    void test();

    // 重写 Object 类中的方法，则该接口中仍然只有上面一个抽象方法，则仍然可以标注 @FunctionalInterface
    @Override
    String toString();
}

public class Test2 {

    public void myTest(MyInterface myInterface) {
        myInterface.test();
        System.out.println("-----------------");
    }

    public static void main(String[] args) {
        Test2 test2 = new Test2();

        // 调用方式：Lambda 表达式
        test2.myTest(() -> {
            System.out.println("this is myTest by Lambda 表达式");
        });

        // 上面等价于下面的，就是  MyInterface 的一个实现。
        // 后面的 Lambda 表达式赋值给了前面的对象引用 myInterface
        MyInterface myInterface = () -> {
            System.out.println("hello");
        };

        // 该 Lambda 表达式的类型
        System.out.println(myInterface.getClass());
        // 该 Lambda 表达式的父类型
        System.out.println("父类" + myInterface.getClass().getSuperclass());
        // 该 Lambda 表达式实现的接口
        System.out.println("实现以下接口：" + myInterface.getClass().getInterfaces()[0]);
    }
}
/**
 * output:
 * this is myTest by Lambda 表达式
 * -----------------
 * class com.gjxaiou.jdk8.lambdaAndFunctionInterface.Test2$$Lambda$2/764977973
 * 父类class java.lang.Object
 * 实现以下接口：interface com.gjxaiou.jdk8.lambdaAndFunctionInterface.MyInterface
 */