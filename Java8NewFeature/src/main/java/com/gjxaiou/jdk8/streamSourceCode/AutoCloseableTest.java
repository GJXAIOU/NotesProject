package com.gjxaiou.jdk8.streamSourceCode;

public class AutoCloseableTest implements AutoCloseable {

    public void doSomething() {
        System.out.println("doSomething invoked!");
    }

    @Override
    public void close() throws Exception {
        System.out.println("close invoked!");
    }

    public static void main(String[] args) throws Exception {
        // close() 会被自动调用
        try (AutoCloseableTest autoCloseableTest = new AutoCloseableTest()) {
            autoCloseableTest.doSomething();
        }
        /**
         * 之前的方式
         */
//        try {
//
//        } catch () {
//
//        } finally {
//            // 显示关闭资源
//        }
    }
}
