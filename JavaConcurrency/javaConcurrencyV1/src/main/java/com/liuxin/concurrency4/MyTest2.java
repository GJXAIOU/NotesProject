package com.liuxin.concurrency4;

import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/**
 * 传统上，我们可以通过synchronized关键字 ＋ wait + notify/notifyAll、来实现多个线程之间的协调与通信，整个过程都是由JvM来帮助我们实现的;
 * 开发者无需(也是无法)了解底层的实现细节
 *
 * 从JDK 5开始，并发包提供了i.ock,Condition(await与signal/signalAll)来实现多个线程之间的协调与通信，整个过程都是由开发者来控制的,
 * 而且相比于传统方式，更加灵活，功能也更加强大
 *
 * Thread.sleep与await(或是object的wait方法)的本质区别: sleep方法本质上不会释放锁，而await会释放锁，并且在signal后，
 * 还需要重新获得锁才能继续执行(该行为与object的wait方法完全一致)
 *
 * 该类实现的例子的描述：实现对容器的放置和取出的操作，在这个操作过程中保证在某一个时刻只能有一个线程在进行使用和操作。
 *
 */
public class MyTest2 {
    public static void main(String[] args) {
        BoundedContainer boundedContainer = new BoundedContainer();

        // 创建10个放置元素的线程，这里使用lambda表达式来创建同时借助intStream流来创建多个线程
        IntStream.range(0,21).forEach(i ->new Thread(()->{
            try {
                boundedContainer.put("liuxin");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());

        // 创建10个获取元素的线程，
        IntStream.range(0,10).forEach(i ->new Thread(()->{
            try {
                boundedContainer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start());


    }

}

/**
 * 有界的容器实现同步的功能这个和使用collections的工具类来实现相应的操作有什么不同呢？（还有一个就是并发的concurrentHashMap）有什么不同
 */
class BoundedContainer{
    /**
     * 定义字符串的容器
     */
    private String[] elements = new String[10];
    /**
     * 定义线程安全的锁
     */
    private Lock lock = new ReentrantLock();
    /**
     * 实现取和放置元素的单独操作
     */
    private Condition notEmptyCondition = lock.newCondition();
    private Condition notFullCondition = lock.newCondition();
    /**
     * 定义数组容器中实际放置的元素
     */
    private int size;
    /**
     * 定义放置元素的索引和取出元素的索引
     */
    private int putIndex;
    private int takeIndex;

    /**
     * 放置元素去容器的操作
     * @param s
     */
    public void put(String s) throws InterruptedException{
       lock.lock();
        try {
            while (size == elements.length)
                // 等待不为满
                notFullCondition.await();
            elements[putIndex] = s;
            size++;
            // 放置越界，进行重置
            if (++putIndex == elements.length)
                putIndex = 0;
            System.out.println("put String:"+Arrays.toString(elements));
            // 放完元素之后，如果有等待取的线程，通知他取线程。
            notEmptyCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 从容器中获取元素的操作
     * @return
     */
    public String take() throws InterruptedException{
        lock.lock();
        try {
            while (size == 0)
                notEmptyCondition.await();
            String s = elements[takeIndex];
            size--;
            if (++takeIndex == elements.length)
                takeIndex = 0;
            System.out.println("take method: "+ Arrays.toString(elements));
            notFullCondition.signal();
            return s;
        } finally {
            lock.unlock();
        }
    }
}
