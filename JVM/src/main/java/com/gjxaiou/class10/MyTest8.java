package com.gjxaiou.class10;


import java.util.Random;

public class MyTest8{
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         *   这里因为 x 前面有 final，所有是一个编译期常量，所有编译之后就会放在 MyTest8 类的常量池中，
         *   所以编译完之后 MyTest8 和 FinalTest 类之间就没有任何关系
         *   因此静态代码块都没有执行，因此 FinalTest 类都没有被初始化，所有将 FinalTest.class删除,代码仍然可以执行
         */
//        System.out.println(FinalTest.x);
       // 这里 y 值在编译期确定不了，得运行期使用该类。
       System.out.println(FinalTest.y);
    }
}
class FinalTest{
    public static final int x = 3;
    public static final int y = new Random().nextInt(3);
    static {
        System.out.println("FinalTest static block");
    }
}
