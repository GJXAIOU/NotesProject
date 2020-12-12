package com.gjxaiou.springcloud.controller;

import com.gjxaiou.springcloud.entities.CommonResult;
import com.gjxaiou.springcloud.entities.Payment;
import com.gjxaiou.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 16:42
 */
@RestController
@Slf4j
public class OrderFeignController {
    @Resource
    private PaymentFeignService paymentFeignService;

    @GetMapping(value = "/consumer/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentFeignService.getPaymentById(id);
    }

    @GetMapping(value = "/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // 默认 OpenFeign 客户端是默认等待 1s
        return paymentFeignService.paymentFeignTimeout();
    }
}
