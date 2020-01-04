package com.gjxaiou.bytecode;


/**
 * 方法的静态分派。
 * Grandpa g1 = new Father();
 * 以上代码, g1的静态类型(声明的类型)是Grandpa,而g1的实际类型(真正指向的类型)是Father.
 * 我们可以得出这样一个结论:变量的静态类型是不会发生变化的,而变量的实际类型则是可以发生变化的(多态的一种体现)
 * 实际变量是在运行期方可确定
 */
class Grandpa {
}

class Father extends Grandpa {
}

class Son extends Father {
}

public class MyTest5 {
    //方法重载,是一种静态的行为,在调用方法时候，JVM唯一判断依据就是根据该方法本身接收的参数（声明的参数类型）来决定调用哪一个方法，编译期就可以完全确定
    public void test(Grandpa grandpa) {
        System.out.println("Grandpa");
    }

    public void test(Father father) {
        System.out.println("father");
    }

    public void test(Son son) {
        System.out.println("Son");
    }

    public static void main(String[] args) {
        MyTest5 myTest5 = new MyTest5();
        Grandpa g1 = new Father();
        Grandpa g2 = new Son();
        myTest5.test(g1);
        myTest5.test(g2);
    }
}
/**
 * output:
 * Grandpa
 * Grandpa
 */


