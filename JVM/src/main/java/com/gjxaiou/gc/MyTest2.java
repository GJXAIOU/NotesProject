package com.gjxaiou.gc;

/**
 * @Author GJXAIOU
 * @Date 2019/12/15 10:27
 */
public class MyTest2 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] bytes = new byte[5 * size];
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
