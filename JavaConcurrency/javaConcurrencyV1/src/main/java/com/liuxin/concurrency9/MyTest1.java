package com.liuxin.concurrency9;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * int corePoolSize∶线程池当中所一直维护的线程数量，如果线程池处于任务空闲期间，那么该线程也并不会被回收掉
 * int maximumPoolSize:线程池中所维护的线程数的最大数量
 * long keepAliverime:超过了corePoolSize的线程在经过
 * keepAliverime时间后如果一直处于空闲状态，那么超过的这部分线程将会被回收掉
 * TimeUnit unit:指的是
 * keepAliveTime的时间单位
 * BlockingQueue<Runnable> workQueue:向线程池所提交的任务位于的阻塞队列，它的实现有多种方式
 * ThreadFactory threadFactory﹑线程工厂，用于创建新的线程并被线程池所管理，默认线程工厂所创建的线程都是用户线程且优先级为正常优先级
 * RejectedBxecutionandler handler:表示当线程池中的线程都在忙于执行任务且阻塞队列也已经满了的情况下，新到来的任务该如何被对待和处理。它有四种实现策略:
 *      AbortPolicy:直接抛出一个运行期异常。
 *      DiscardPolicy:默默地丢弃掉提交的任务，什么都不做且不抛出任何异常
 *      DiscardoldestPolicy:丢弃掉阻塞队列中存放时间最久的任务(队头元素)，并且为当前所提交的任务留出一个队列中的空闲空间，以便将其放进到队列中
 *      callerRunsPolicy:直接由提交任务的线程来运行这个提交的任务
 *
 *  在线程池中，尽量将偏向锁的标记关闭（因为线程池本来就是用来处理多线程的）
 */
public class MyTest1 {

    public static void main(String[] args) {
        /*
         * 使用工厂的方式创建线程池，注意这里建议在自己创建线程池的时候不要使用工程模式来创建线程池。为什么呢？
         * 因为ThreadPoolExecutor构造方法有很多参数，所有很多时候是根据具体业务需求进行定制的。比如阻塞队列使用
         * 工厂模式来进行创建话默认是创建无限大小的阻塞队列（然后在高并发的情况下，很有可能会出现内存溢出的情况），还有就是
         * 其默认是使用的拒绝策略就是抛异常（即使不抛异常使用内置的四种拒绝方法依然不符合实际要求）
         */
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();
        ExecutorService executorService3 = Executors.newCachedThreadPool();
        // 创建任务的个数
        IntStream.range(0, 50).forEach(i -> {
            executorService.submit(() -> {
                IntStream.range(0, 50).forEach(j -> {
                    // 打印执行当前任务的线程的名称
                    System.out.println(Thread.currentThread().getName());
                });
            });
        });

        // 关闭线程池，因为这不是守护线程，一旦主线程执行完毕之后如果没有关闭的话虚拟机会一直执行
        executorService.shutdown();

    }
}
