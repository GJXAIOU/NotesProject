package com.gjxaiou.springcloud.controller;

import com.gjxaiou.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 20:48
 */
@RestController
@Slf4j
// 如果配置了 fallbackMethod 就用指定了，否则针对 @HystrixCommand 标注的方法对应全局默认 fallback 方法
@DefaultProperties(defaultFallback = "paymentGlobalFallback")
public class OrderHystrixController {
    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    String paymentInfoOK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoOK(id);
        log.info("feign-hystrix-order80=> paymentInfoOK");
        return result;
    }


    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
//    @HystrixCommand(fallbackMethod = "paymentInfoTimeoutFallback", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value =
//                    "1500")
//    })
    @HystrixCommand
    String paymentInfoTimeout(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.paymentInfoTimeout(id);
        log.info("调用 feign-hystrix-order80=> paymentInfoTimeout");
        return result;
    }

    String paymentInfoTimeoutFallback(@PathVariable("id") Integer id) {
        log.info("调用 feign-hystrix-order80=> paymentInfoTimeoutFallback");
        return "消费者 80，对方 8001 系统繁忙，请等待一段时间再试或者检查自身是否出错";
    }

    // 设置全局 fallback
    public String paymentGlobalFallback() {
        return "80 全局性的fallback";
    }
}
