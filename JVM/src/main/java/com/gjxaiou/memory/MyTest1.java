package com.gjxaiou.memory;

import java.util.ArrayList;
import java.util.List;

public class MyTest1 {
    public static void main(String[] args) {
        //-Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError 设置jvm对空间最小和最大以及遇到错误时把堆存储文件打印出来
        //打开jvisualvm 装在磁盘上的转存文件
        List<MyTest1> list = new ArrayList<>();
        while (true) {
            list.add(new MyTest1());
            System.gc();
        }
    }
}