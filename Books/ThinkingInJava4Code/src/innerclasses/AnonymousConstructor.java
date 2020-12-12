//: innerclasses/AnonymousConstructor.java
// Creating a constructor for an anonymous inner class.

import static net.mindview.util.Print.*;

abstract class Base {
    public Base(int i) {
        print("Base constructor, i = " + i);
    }

    public abstract void f();
}

public class AnonymousConstructor {
    public static Base getBase(int i) {
        /*
        首先匿名内部类相当于一个类 extend Base，因此可以使用基类Base
        的构造方法进行初始化
        同时自己的构造也可以使用下面的构造代码块进行初始化
         */
        return new Base(i) {
            {
                print("Inside instance initializer");
            }

            @Override
            public void f() {
                print("In anonymous f()");
            }
        };
    }

    public static void main(String[] args) {
        Base base = getBase(47);
        base.f();
    }
} /* Output:
Base constructor, i = 47
Inside instance initializer
In anonymous f()
*///:~
