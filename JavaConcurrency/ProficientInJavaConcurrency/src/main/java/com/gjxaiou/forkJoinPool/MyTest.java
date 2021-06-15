package com.gjxaiou.forkJoinPool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * 计算 10 ~ 100 的和
 * 任务大小判断：任务两个值之间相差超过 5 就是大任务，需要进行划分
 * 因为该任务需要返回结果，因此任务需要继承 RecursiveTask
 */
public class MyTest {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        MyTask myTask = new MyTask(10, 100);
        Integer result = forkJoinPool.invoke(myTask);

        System.out.println(result);

        forkJoinPool.shutdown();
    }
}

// 定义任务，需要返回值则继承 RecursiveTask，其中 <> 中为任务返回值类型
class MyTask extends RecursiveTask<Integer> {
    // 对于任务的基本属性设置

    // 如果两个数差值超过 limit 则需要进行拆解
    private int limit = 4;

    // 设置计算值的范围
    private int first;
    private int last;

    // 通构造方法传入
    MyTask(int first, int last) {
        this.first = first;
        this.last = last;
    }

    @Override
    protected Integer compute() {
        int result = 0;
        // 任务的实际差值
        int gap = last - first;
        boolean flag = gap <= limit;

        if (flag) {
            // 划分之后的可以执行的任务的线程
            System.out.println(Thread.currentThread().getName());
            for (int i = first; i <= last; i++) {
                result += i;
            }
        } else {
            // 任务进行拆分，可以拆分为多个，这个由开发者自行定义，这里是按照中点拆分为两个
            int middle = (first + last) / 2;
            // 拆分之后是两个小任务，因此还要构建 MyTasK 任务对象
            MyTask leftTask = new MyTask(first, middle);
            MyTask rightTask = new MyTask(middle + 1, last);

            // 进行合并并且递归：如果 A 线程调用了 invokeAll，则 A 线程会执行第一个任务，另一个任务会给其它线程执行
            invokeAll(leftTask, rightTask);

            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            result = leftResult + rightResult;
        }
        return result;
    }
}


