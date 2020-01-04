package com.gjxaiou.gc;

/**
 * @Author GJXAIOU
 * @Date 2019/12/15 13:27
 */
public class MyTest4 {
    public static void main(String[] args) throws InterruptedException {
        // 下面两个字节数组在 main() 方法中，不会被GC
        byte[] byte1 = new byte[512 * 1024];
        byte[] byte2 = new byte[512 * 1024];

        myGC();
        Thread.sleep(1000);
        System.out.println("----111111111------");
        myGC();
        Thread.sleep(1000);
        System.out.println("----22222222------");
        myGC();
        Thread.sleep(1000);
        System.out.println("----333333333------");
        myGC();
        Thread.sleep(1000);
        System.out.println("----444444444------");

        byte[] byte3 = new byte[1024 * 1024];
        byte[] byte4 = new byte[1024 * 1024];
        byte[] byte5 = new byte[1024 * 1024];
        myGC();
        Thread.sleep(1000);
        System.out.println("----555555555------");
        myGC();
        Thread.sleep(1000);
        System.out.println("----666666666------");

        System.out.println("hello world");

    }

    // 方法中定义的变量当方法执行完成之后生命周期就结束了，下次垃圾回收时候就可以回收了
    private static void myGC() {
        for (int i = 0; i < 40; i++) {
            byte[] byteArray = new byte[1024 * 1024];
        }
    }
}
