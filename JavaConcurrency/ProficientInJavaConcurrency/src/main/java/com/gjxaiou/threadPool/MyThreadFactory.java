package com.gjxaiou.threadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;


public class MyThreadFactory implements ThreadFactory {
    // 因为在系统中可能创建多个线程池，并且则多个线程池可能都是由 DefaultThreadFactory 来创建生成的，所以该变量设置为 static
    // 之后，所有线程池都基于该变量进行增加。Pool-1
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private ThreadGroup group;
    // 线程是归属于线程池的，一个线程不可能归属多个线程池，所以一定是归属于某一个工厂对象的。
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private String namePrefix;

    MyThreadFactory(String namePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        // 如果创建的线程是守护线程
        if (t.isDaemon())
            // 将其变成用户线程
            t.setDaemon(false);
        // 设置新建线程的优先级为默认优先级
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}