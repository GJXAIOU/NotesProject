package com.gjxaiou.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author GJXAIOU
 */
@Slf4j
public class FutureExample {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            // 让线程 sleep 一会，相当于完成某件事情需要较长时间
            log.info("do something in callable");
            Thread.sleep(5000);
            return "Done";
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 接收另外一个线程执行的结果
        Future<String> future = executorService.submit(new MyCallable());
        // 另一个线程在做其他事情
        log.info("do something in main");
        Thread.sleep(1000);
        // 获取之前任务返回值，如果值没有计算完，或阻塞等待，这里可以看到最终日志前面有 5s
        String result = future.get();
        log.info("result：{}", result);
    }
}
