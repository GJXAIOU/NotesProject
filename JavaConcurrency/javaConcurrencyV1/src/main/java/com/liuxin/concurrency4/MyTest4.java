package com.liuxin.concurrency4;

/**
 * Java内存模型(Java Memory Model, JMM) 以及happen- -before
 * .变量的原子性问题。
 * 2.变量的可见性问题
 * 3.变量修改的时序性问题。
 *
 * happen-before重要规则: .
 * 1.顺序执行规则(限定在单个线程上的) :该线程的每个动作都happen-before它的后面的动作。（指令重排序和happen-before是不矛盾的）
 * 2.隐式锁(monitor) 规则: unlock happen- before lock, 之前的线程对于同步代码块的所有执行结果对于后续获取锁的线程来说都是可见的。
 * 3. volatile读写规则:对于-个volatile变量的写操作一定会happen-before后续对该变量的读操作。
 * 4.多线程的启动规则: Thread对象的start方法happen-before该线程run方法中的任何-一个动作，包括在其中启动的任何子线程。
 * 5.多线程的终正规则- 个线程启动了一个子线程，并且调用了子线程的join方法等待其结束，那么当子线程结束后，父线程的接下来的所有操作
 * 都可以看到子线程run方法中的执行结果。
 * 6.线程的中断规则:可以调用interrupt方法来中断线程，这个调用happen-before对该线程 中断的检查(isInterrupted) 。
 *
 *
 */
public class MyTest4 {
}
