package com.gjxaiou.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * 三个子任务都完成则主任务可以继续执行
 */
public class MyTest1 {
    public static void main(String[] args) {
        // 参数表示实际执行子任务的数量，该值传给了 countDownLatch 中的一个计数器
        CountDownLatch countDownLatch = new CountDownLatch(3);
        // 创建三个线程
        IntStream.range(0,3).forEach(i -> new Thread(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("hello");
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }finally {
                // 每执行一次 countDown() 方法，则 countDownLatch 中的计数器的值原子性减一
                // 如果放在 try 中，则如其他代码抛出异常则不再减一，导致所有调用 await 的方法的主线程一直处于等待状态无法执行
                countDownLatch.countDown();
            }
        }).start());

        System.out.println("启动子线程完毕");


        try {
            // 调用 await() 方法时会检查 countDownLatch 对象中的计数器值是否为 0，如果为 0 则 await 方法立刻返回。如果不为 0 则调用
            // await 方法的线程会进入阻塞队列中，等待计数器为 0 之后唤醒所有等待队列中的线程然后执行。
            countDownLatch.await();
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }

        System.out.println("主线程执行完毕");

    }

}
