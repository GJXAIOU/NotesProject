package com.gjxaiou.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    //告诉 Spring 这是一个异步方法，不加这个注解则访问页面之后 5s 之后才会响应。
    @Async
    public void hello() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中...");
    }
}
