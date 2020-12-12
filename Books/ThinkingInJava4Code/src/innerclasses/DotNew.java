//: innerclasses/DotNew.java
// Creating an inner class directly using the .new syntax.

public class DotNew {
    public class Inner {
    }

    public static void main(String[] args) {
        DotNew dn = new DotNew();
        // 创建内部类对象，在new表达式中提供对外部类对象的引用；
        // 必须使用外部类对象来创建内部类对象；
        DotNew.Inner dni = dn.new Inner();
    }
} ///:~
