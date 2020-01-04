package com.gjxaiou.bytecode;

/**
 * 方法的动态分派（运行期才能确定调用哪个方法）
 * 方法的动态分派涉及到一个重要概念:方法接收者（方法到底是由哪个对象来调用的）。
 *
 * invokevirtual 字节码指令的多态查找流程:
 * 首先到操作数的栈顶去寻找栈顶元素所指向的对象的实际类型（不是静态类型）；（这里就是 apple 类型）
 * 如果寻找到了与常量池中描述符和名称都相符的方法（这里就是在 APPle 类中找到一个与<com/gjxaiou/bytecode/Fruit
 * .test>方法的描述符和名称一样的方法），并且具备访问权限，就返回目标方法的直接引用（这里就是返回 Apple 中 test 方法的直接引用），流程结束；
 * 如果找不到，就按照继承关系从子类到父类的一层一层的使用上面的查找流程，一直能找到为止，如果找不到报错；
 *
 * 比较方法重载(overload)与方法重写(overwrite) ,我们可以得到这样一个结论:
 * 方法重载是静态的,是编译期行为;
 * 方法重写是动态的,是运行期行为。
 *
 * 下面就是三个 test 方法的符号引用虽然相同（都是 <com/gjxaiou/bytecode/Fruit.test>），但是在运行期转换成了不同的直接引用
 */
class Fruit {
    public void test() {
        System.out.println("Fruit");
    }
}

class Apple extends Fruit {
    @Override
    public void test() {
        System.out.println("Apple");
    }
}

class Orange extends Fruit {
    @Override
    public void test() {
        System.out.println("Orange");
    }
}

public class MyTest6 {
    public static void main(String[] args) {
        // new 的作用：首先为该对象在堆上开辟一个内存空间，然后执行其构造方法，最后将构造方法执行完后返回的针对在堆上所生成的对象的引用返回；
        /** new 关键字对应于字节码中的下面四个操作：
         *  0 new #2 <com/gjxaiou/bytecode/Apple> // 开辟内存空间并创建对象
         *  3 dup // 将引用的对象的值压入到栈顶
         *  4 invokespecial #3 <com/gjxaiou/bytecode/Apple.<init>>  // 调用对象的构造方法
         *  7 astore_1 // 将对象在堆上的引用返回赋给一个局部变量
         */
        Fruit apple = new Apple();
        Fruit orange = new Orange();

        // 16 aload_1：从局部变量中加载索引为 1 的引用，就是 apple
        apple.test();
        orange.test();

        apple = new Orange();
        apple.test();
        // 上面三个 test() 方法最终对应的字节码都是：invokevirtual #6 <com/gjxaiou/bytecode/Fruit.test>
    }
}
/** output:
 * Apple
 * Orange
 * Orange
 */