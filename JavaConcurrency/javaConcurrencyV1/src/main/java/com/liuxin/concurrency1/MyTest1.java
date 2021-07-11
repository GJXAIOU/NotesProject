package com.liuxin.concurrency1;

/**
 * 在调wait方法时，线程必须持有被调用对象的锁，当调用wait方法后，线程会释放该对象的锁
 * 在Thread类中sleep方法时，线程是不会释放掉对象的锁（monitor），
 *
 *
 * 关于wait和notify和notifyAll方法的总结
 *
 * 1.当调用wait时，首先需要确保调用了wait方法的的线程已经持有了对象的锁
 * 2.当调用wait后，该线程就会释放掉这个对象的锁，然后就会进入等待状态（wait set）
 * 3.当线程调用了wait后进入到等待状态时，它就可以等待其他线程调用相同对象的notify或notifyAll方法来使得自己被唤醒
 * 4.一旦这个线程被其他线程唤醒后，该线程就会与其他线程一同开始竞争这个对象的锁（公平竞争），只有当该线程获得这个对象的锁后，线程才会继续往下执行
 * 5.调用wait方法的代码片段需要放在一个synchronized块或者synchronized房中中，这样才可以确保线程在调用wait方法前已经获得到了对象的锁
 * 6.当调用对象的notify方法时，它会随机唤醒该对象等待集合（wait set）中的任意一个线程，当某个线程被唤醒之后，它就会与其他线程一同竞争对象的锁
 * 7.当调用对象的notifyALL方法时，它会唤醒该对象等待集合中的所有线程，这些线程被唤醒之后，又会开始竞争对象的锁
 * 8.在某一时刻，只要唯一一个线程可以拥有对象的锁
 *
 *
 * @author leany
 * @date 2020/11/8 18:27
 */
public class MyTest1 {

    /**
     *
     * @param args
     * @throws InterruptedException
     */

    public static void main(String[] args) throws InterruptedException {
        Object object = new Object();

        // 没有拿到锁所以抛出了异常
        // object.wait();

        // 怎么持有锁
        synchronized (object){
            // 一旦执行了等待就会释放锁
            object.wait();
        }



    }


}


class Lock{


    // 锁是this
    public synchronized void method1(){

    }

    // 锁是Lock.class
    public static synchronized void method2(){

    }
}