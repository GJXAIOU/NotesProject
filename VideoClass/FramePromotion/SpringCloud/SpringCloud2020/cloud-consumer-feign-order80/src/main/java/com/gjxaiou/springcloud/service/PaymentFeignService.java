package com.gjxaiou.springcloud.service;

import com.gjxaiou.springcloud.entities.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 16:37
 */
@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {
    // 8001 的 PaymentService 接口中提供了两个方法：getPaymentById() 和 createPayment();这里仅对应实现一个作为示例。
    // 将业务提供者的controller路径和方法复制粘贴进来
    @GetMapping("/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id);


    /**
     * 仅仅用于测试 Feign 的超时设置
     */
    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout();
}
