//: exceptions/InheritingExceptions.java
// Creating your own exceptions.

// 自定义异常类，提供默认的构造器，调用的是基类Exception的构造器
class SimpleException extends Exception {
}

public class InheritingExceptions {
    public void f() throws SimpleException {
        System.out.println("Throw SimpleException from f()");
        throw new SimpleException();
    }

    public static void main(String[] args) {
        InheritingExceptions sed = new InheritingExceptions();
        try {
            sed.f();
        } catch (SimpleException e) {
            System.out.println("Caught it!");
        }
    }
} /* Output:
Throw SimpleException from f()
Caught it!
*///:~
