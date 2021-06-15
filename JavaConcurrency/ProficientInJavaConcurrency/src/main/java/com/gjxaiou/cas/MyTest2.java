package com.gjxaiou.cas;

import java.util.concurrent.atomic.AtomicInteger;

public class MyTest2 {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.get());
        // 将值设置为 8 并且返回旧的值
        System.out.println(atomicInteger.getAndSet(8));
        System.out.println(atomicInteger.get());
        // 将值自增 1 然后返回旧值
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());
    }
}
