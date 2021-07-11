package com.liuxin.concurrency6;

/**
 * CAS
 * 1. synchronized关键字与Lock等锁机制都是悲观锁:无论做何种操作，首先都需要先上锁，接下来再去执行后续操作，从而确保了
 * 接下来的所有 操作都是由当前这个线程来执行的。
 * 2.乐观锁:线程在操作之前不会做任何预先的处理，而是直接去执行;当在最后执行变量更新的时候， 当前线程需要有-种机制来确保
 * 当前被操作的变量是没有被其他线程修改的; CAS是乐观锁的一种极为重要的实现方式。
 *
 * CAS (Compare And Swap)
 * 比较与交换:这是一个不断循环的过程，- 直到变量值被修改成功为止。CAS本身是由硬件指令来提供支持的，换句话说，硬件中是通过-个
 * 原子指令来实现比较与交换的;因此，CAS可以确保变量操作的原子性的。
 */
public class MyTest1 {
    private int count;

    /**
     * 为了适合于多线程的操作
     * @return
     */
    public synchronized int getCount() {
        return count;
    }

    /**
     * 读取->修改->写入:这三个操作并非原子操作
     * @param count
     */
    public synchronized void setCount(int count) {
        this.count = count;
    }
}
