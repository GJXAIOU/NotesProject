package com.gjxaiou.gc;

/**
 * @Author GJXAIOU
 * @Date 2019/12/13 20:50
 */
public class MyTest1 {
    public static void main(String[] args) {
        int size = 1024 * 1024;
        byte[] myAlloc1 = new byte[2 * size];
        byte[] myAlloc2 = new byte[2 * size];
        byte[] myAlloc3 = new byte[2 * size];
       // byte[] myAlloc4 = new byte[3 * size];
        System.out.println("hello world");
    }
}
