package com.gjxaiou.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 19:25
 */
@Service
public class PaymentService {
    /**
     * 服务降级
     */

    /**
     * 分别提供一个正常访问和一个访问出错(这里是访问很慢)的方法
     */
    public String paymentInfoTrue(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoTrue, id: " + id +
                " 访问成功方法";
    }


    // 含义：设置超时限定时间为： 3s，超时就不调用这个方法，反而调用 paymentInfoFalseHandler 处理。
    @HystrixCommand(fallbackMethod = "paymentInfoFalseHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value =
                    "3000")
    })
    // 故意耗时 5s
    public String paymentInfoFalse(Integer id) {
        int timeNum = 2;
        try {
            TimeUnit.SECONDS.sleep(timeNum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池：" + Thread.currentThread().getName() + " paymentInfoFalse, id: " + id +
                " 访问失败方法，共耗时：" + timeNum;
    }

    // 提供 fallback 方法
    public String paymentInfoFalseHandler(Integer id) {
        return "线程池：" + Thread.currentThread().getName() + " 系统繁忙，使用 paymentInfoFalseHandler 处理, " +
                "id: " + id + " 访问失败备份方法";
    }


    /**
     * 服务熔断
     */
    // Hystrix 的全部属性在类：HystrixCommandProperties 中
    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerHandler", commandProperties = {
            // 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            // 请求次数
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            // 时间窗口期：经过多长时间后恢复一次尝试
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            // 失败率阈值
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            throw new RuntimeException("id 不能为负数");
        }
        String simpleUUID = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + " 调用成功，流水号为：" + simpleUUID;
    }

    // 对应的服务降级 fallback 方法
    public String paymentCircuitBreakerHandler(@PathVariable("id") Integer id) {
        return "id 不能为负数，请稍后再试，This is fallback method.";
    }

}
