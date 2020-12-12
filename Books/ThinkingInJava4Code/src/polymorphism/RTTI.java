//: polymorphism/RTTI.java
// Downcasting & Runtime type information (RTTI).
// {ThrowsException}

class Useful {
    public void f() { }

    public void g() { }
}

class MoreUseful extends Useful {
    @Override
    public void f() { }

    @Override
    public void g() { }

    public void u() { }

    public void v() { }

    public void w() { }
}

public class RTTI {
    public static void main(String[] args) {
        Useful[] x = {
                new Useful(),
                // 这里相当于向上转型
                new MoreUseful()
        };
        x[0].f();
        x[1].g();
        // Compile time: method not found in Useful:
        //! x[1].u();// 该句不能执行，会报错
        ((MoreUseful) x[1]).u(); // Downcast/RTTI
        ((MoreUseful) x[0]).u(); // Exception thrown
    }
}/*output:
Exception in thread "main" java.lang.ClassCastException: Useful cannot be cast to MoreUseful
	at RTTI.main(RTTI.java:43)
*/
///:~
