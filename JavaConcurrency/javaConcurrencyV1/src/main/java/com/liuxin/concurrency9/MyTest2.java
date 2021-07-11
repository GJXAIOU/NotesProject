package com.liuxin.concurrency9;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * 对于线程池来说，其提供了execute与submit两种方式来向线程池提交任务
 * 总体来说，submit方法是 可以取代execute方法的，因为它既可以接收Callable任务, 也可以接收Runnable任务。
 * 关于线程池的总体执行策略:
 * 1.如果线程池中正在执行的线程数< corePoolSize, 那么线程池就会优先选择创建新的线程而非将提交的任务加到阻塞队列中。
 * 2.如果线程池中正在执行的线程数>= corePoolSize, 那么线程池就会优先选择对提交的任务进行阻塞排队而非创建新的线程。
 * 3.如果提交的任务无法加入到阻塞队列当中，那么线程池就会创建新的线程;如果创建的线程数超过了maximumPoolSize,那么拒绝策略就会起作用。
 *
 *
 * 关于线程池任务提交的总结:
 * 1.两种提交方式: submit与execute
 * 2. submit有 三种方式，无论哪种方式，最终都是将传递进来的任务转换为- -个callable对象进行处理
 * 3.当callable对象构造完毕后， 最终都会调用Executor接口中声明的execute方法进行统- -的处理
 * 对于线程池来说，存在两个状态需要维护:
 * 1.线程池本身的状态: ct1的高三位来表示
 * 2.线程池中所运行着的线程的数量: ct1的其余29位来表示
 *
 * 线程池一共存在5种状态:
 * 1. RUNNING: 线程池可以接收新的任务提交,并且还可以正常处理阻塞队列中的任务。
 * 2. SHUTDOWN: 不再接收新的任务提交,不过线程池可以继续处理阻塞队列中的任务。
 * 3. STOP: 不再接收新的任务，同时还会丢弃阻塞队列中的既有任务;此外，它还会中断正在处理中的任务。
 * 4. TIDYING: 所有的任务都执行完毕后(同时也涵盖了阻塞队列中的任务)，当前线程池中的活动的线程数量降为0，将会调用terminated方法。
 * 5. TERMINATED: 线程池的终止状态,当terminated方法执行完毕后，线程池将会处于该状态之下。
 *
 * RUNNING -> SHUTDOWN: 当调用了线程池的shutdown方法时，或者当f inalize方法被隐式调用后(该方法内部会 调用shutdown方法)
 * RUNNING,SHUTDOWN -> STOP: 当调用了线程池的shutdownNow方法时
 * SHUTDOWN -> TIDYING: 在线程池与阻塞队列均变为空时
 * STOP -> TIDYING: 在线程池变为空时
 * TIDYING- -> TERMINATED: 在terminated方法被执行完毕时
 *
 *
 */
public class MyTest2 {
    public static void main(String[] args) {
        /*
         * 这里可以通过观察在创建线程池使用的策略的不同其产生的效果的不同，比如
         * AbortPolicy，产生的效果抛出异常
         *
         */
        ExecutorService executorService = new ThreadPoolExecutor(3,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.AbortPolicy());
        ExecutorService executorService2 = new ThreadPoolExecutor(3,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.DiscardPolicy());
        ExecutorService executorService3 = new ThreadPoolExecutor(3,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3), new ThreadPoolExecutor.DiscardOldestPolicy());
        ExecutorService executorService4 = new ThreadPoolExecutor(3,5,0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>(3),new LeanyThreadFactory("order"), new ThreadPoolExecutor.CallerRunsPolicy());


        // 创建任务的个数，比如任务的个数为6，7，8，9这些数字
        IntStream.range(0,10).forEach(i -> {
            Future result = executorService2.submit(() -> {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 打印线程池的执行信息
                System.out.println(Thread.currentThread().getName()+"处理第"+i+"个任务");
            });
            try {
                // 据说这里丢弃任务之后，在执行get方法的时候会出现永远退不出程序的情况，具体不太清楚
                result.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        // 关闭线程池，因为这不是守护线程，一旦主线程执行完毕之后如果没有关闭的话虚拟机会一直执行
        executorService.shutdown();
        executorService2.shutdown();
        executorService3.shutdown();
        executorService4.shutdown();
    }
}

class LeanyThreadFactory implements ThreadFactory {
    private static final AtomicInteger poolNumber = new AtomicInteger(1);
    private final ThreadGroup group;
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    private final String namePrefix;

    LeanyThreadFactory(String threadPoolName) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() :
                Thread.currentThread().getThreadGroup();
        namePrefix = threadPoolName +
                poolNumber.getAndIncrement() +
                "-thread-";
    }

    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);
        if (t.isDaemon())
            t.setDaemon(false);
        if (t.getPriority() != Thread.NORM_PRIORITY)
            t.setPriority(Thread.NORM_PRIORITY);
        return t;
    }
}
