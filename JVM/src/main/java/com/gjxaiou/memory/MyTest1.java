package com.gjxaiou.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * VM options 设置； -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
 * 设置 jvm 堆空间的最小和最大值（如果两值相同则堆不会自动扩展）以及遇到内存溢出异常时 Dump 出当前的内存堆转储快照，便于以后分析。
 * 然后通过 JVisualVM 装载磁盘上的转存文件来具体分析
 */
public class MyTest1 {
    public static void main(String[] args) {
        List<MyTest1> list = new ArrayList<>();
        while (true) {
            list.add(new MyTest1());
            System.gc();
        }
    }
}