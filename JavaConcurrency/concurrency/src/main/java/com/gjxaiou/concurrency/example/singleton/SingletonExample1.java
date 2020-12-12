package com.gjxaiou.concurrency.example.singleton;

import com.gjxaiou.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用时进行创建
 */
@NotThreadSafe
public class SingletonExample1 {

    // 私有构造函数
    private SingletonExample1() {

    }

    // 单例对象
    private static SingletonExample1 instance = null;

    // 静态的工厂方法
    public static SingletonExample1 getInstance() {
        // 在判断 instance 是否为空的时候可能存在线程不安全的现象
        if (instance == null) {
            instance = new SingletonExample1();
        }
        return instance;
    }
}
