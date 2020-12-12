//: innerclasses/Callbacks.java
// Using inner classes for callbacks
package innerclasses;

import static net.mindview.util.Print.*;

interface Incrementable {
    void increment();
}

// Very simple to just implement the interface:
class Callee1 implements Incrementable {
    private int i = 0;

    // e.执行输出 1
    @Override
    public void increment() {
        i++;
        print(i);
    }
}

class MyIncrement {
    // 4.执行输出：Other operation
    public void increment() {
        print("Other operation");
    }

    // 执行返回c2.increment()
    static void f(MyIncrement mi) {
        mi.increment();
    }
}

// If your class must implement increment() in
// some other way, you must use an inner class:
class Callee2 extends MyIncrement {
    private int i = 0;

    // 2.执行c2.increment()
    @Override
    public void increment() {
        // 3.执行MyIncrement.increment()
        super.increment();
        i++;
        // 5.输出 1
        print(i);
    }

    private class Closure implements Incrementable {
        @Override
        public void increment() {
            // Specify outer-class method, otherwise
            // you'd get an infinite recursion:
            Callee2.this.increment();
        }
    }

    Incrementable getCallbackReference() {
        return new Closure();
    }
}

class Caller {
    private Incrementable callbackReference;

    // b.执行 callbackReference = c2
    Caller(Incrementable cbh) {
        callbackReference = cbh;
    }

    // d.执行 c1.increament()
    void go() {
        callbackReference.increment();
    }
}

public class Callbacks {
    public static void main(String[] args) {
        Callee1 c1 = new Callee1();
        Callee2 c2 = new Callee2();
        // 存在向上转型
        // 1.返回 c2.increment()
        MyIncrement.f(c2);
        // a.因为c1为Callee1对象，向上转型为Incrementable对象
        Caller caller1 = new Caller(c1);
        Caller caller2 = new Caller(c2.getCallbackReference());
        // c.执行caller1.go()
        caller1.go();
        caller1.go();
        caller2.go();
        caller2.go();
    }
} /* Output:
Other operation
1
1
2
Other operation
2
Other operation
3
*///:~
