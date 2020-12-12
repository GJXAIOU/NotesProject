package com.gjxaiou.hand;

public class DynamicDispatch {
    static abstract class Human {
        protected abstract void sayHello();
    }

    static class Man extends Human {
        @Override
        protected void sayHello() {
            System.out.println("Man say Hello");
        }
    }

    static class Woman extends Human {
        @Override
        protected void sayHello() {
            System.out.println("WoMan say Hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();  // Man say Hello
        woman.sayHello(); // WoMan say Hello
        man = new Woman();
        man.sayHello(); // WoMan say Hello
    }
}
