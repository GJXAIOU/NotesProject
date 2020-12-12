package com.gjxaiou.gc;


import org.junit.Test;

public class ReferenceCountingGC {
    public Object instance = null;
    private static final int memory = 1024 * 1024;
    /**
     * 该成员属性作用为：占用内存，以便能在 GC 日志中看清楚是否被回收过
     */
    private byte[] bigSize = new byte[2 * memory];

    @Test
    public  void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();
        objA.instance = objB;
        objB.instance = objA;
        objA = null;
        objB = null;

        // 直接进行 GC
        System.gc();
    }
}
