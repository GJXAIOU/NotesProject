package com.gjxaiou.threadPool;

import java.util.concurrent.*;
import java.util.stream.IntStream;

public class MyTest4 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 因为 ExecutorCompletionService 依赖于线程池，首先创建线程池对象
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(4, 10, 10,
                TimeUnit.SECONDS, new LinkedBlockingQueue<>(20),
                new ThreadPoolExecutor.AbortPolicy());

        CompletionService<Integer> completionService =
                new ExecutorCompletionService<>(threadPoolExecutor);

        // 向线程池提交任务，设置每个任务执行时间不同；任务是通过 CompletionService 通过 submit 向线程池提交任务
        IntStream.range(0, 10).forEach(i -> {
            completionService.submit(() -> {
                // 定义 Callable 实例
                Thread.sleep((long) (Math.random() * 1000));
                System.out.println(Thread.currentThread().getName());
                return i * i;
            });
        });

        // 获取结果
        for (int i = 0; i < 10; i++) {
            Integer result = completionService.take().get();
            System.out.println(result);
        }

        threadPoolExecutor.shutdown();
    }
}
