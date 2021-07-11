package com.liuxin.concurrency8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 对于ReentrantLock来说,其执行逻辑如下所示:
 * 1．尝试获取对象的锁，如果获取不到（意味着已经有其他线程持有了锁，并且尚未释放)，那么它就会进入到AQS的阻塞队列当中。
 * 2。如果获取到，那么根据锁是公平锁还是非公平锁来进行不同的处理
 *      2.1如果是公平锁,那么线程会直接放置到AQs阻塞队列的末尾
 *      2.2 如果是非公平锁，那么线程会首先尝试进行CAs计算，如果成功，则直接获取到锁;如果失败，则与公平锁的处理方式—致，被放到阻塞队列末尾
 * 3．当锁被释放时《调用了unlock方法)，那么底层会调用release方法对state成员变量值进行减一操作，如果减一后,state值不为0，那么release操作就执行完毕;
 *    如果减一操作后，state值为O，则调用LockKSupport的unpark方法唤醒该线程后的等待队列中的第一个后继线程(pthread_mutex_unlock)，将其唤醒，
 *    使之能够获取到对象的锁(release时，对于公平锁与非公平锁的处理逻辑是一致的)﹔之所以调用release方法后state值可能不为零，原因在于ReentrantLock是可重入锁，
 *    表示线程可以多次调用lock方法，导致每调用一次,state值都会加一。
 *
 * 对于ReentrantLock来说，所谓的上锁，本质上就是对AQS中的state成员变量的操作∶对该成员变量+1，表示上锁;对该成员变量-1，表示释放锁。（
 * 看来需要分析一下文档）
 */
public class MyTest1 {
    private Lock lock = new ReentrantLock();

    public void method() {
        try {
            lock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MyTest1 myTest1 = new MyTest1();

        IntStream.range(0,10).forEach(i->{
            new Thread(()->{
               myTest1.method();
            }).start();
        });


    }

}



