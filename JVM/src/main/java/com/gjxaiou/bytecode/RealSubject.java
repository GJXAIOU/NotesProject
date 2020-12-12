package com.gjxaiou.bytecode;

public class RealSubject  implements Subject{
    @Override
    public void request() {
        System.out.println("From real subject");
    }
}
