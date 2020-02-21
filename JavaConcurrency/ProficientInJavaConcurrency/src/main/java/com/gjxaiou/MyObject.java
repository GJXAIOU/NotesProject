package com.gjxaiou;

/**
 * @Author GJXAIOU
 * @Date 2020/2/15 10:51
 */
public class MyObject {
    private int counter;

    // 分别实现递增、递减方法，因为要调用 wait 方法，所以需要使用 synchronized
    public synchronized void increase() {
        // 进入 increase 方法之后，如果 counter 不为 0，则该线程等待
        while (counter != 0) {
            try {
                // 调用 wait 使得释放掉 counter 的锁，使得递减的线程拿到锁
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 当另一个线程执行完递减之后，调用 notify 方法让递增线程唤醒了
        counter++;
        System.out.println(counter);
        // 通知其它线程起来（这里就是指递减的线程）
        notify();
    }

    // 下面的解释同上
    public synchronized void decrease() {
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        counter--;
        System.out.println(counter);
        notify();
    }
}
