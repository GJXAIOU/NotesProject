package com.gjxaiou.cas;

public class MyTest1 {
    private int count;

    public synchronized int getCount() {
        return count;
    }

    public synchronized void increaseCount() {
        // 这行在多线程运行时会出问题，通过字节码进行分析
        ++this.count;
    }
}