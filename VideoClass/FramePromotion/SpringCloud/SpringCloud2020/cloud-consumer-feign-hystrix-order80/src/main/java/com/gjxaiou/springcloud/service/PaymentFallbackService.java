package com.gjxaiou.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 22:13
 */
@Component
public class PaymentFallbackService implements PaymentHystrixService{
    @Override
    public String paymentInfoOK(Integer id) {
        return "PaymentFallbackService => paymentInfoOK => Fallback";
    }

    @Override
    public String paymentInfoTimeout(Integer id) {
        return "PaymentFallbackService => paymentInfoTimeout => Fallback";
    }
}
