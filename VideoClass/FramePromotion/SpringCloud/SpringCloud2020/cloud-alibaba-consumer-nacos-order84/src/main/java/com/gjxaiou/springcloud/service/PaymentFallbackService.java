package com.gjxaiou.springcloud.service;

import com.gjxaiou.springcloud.entities.Payment;
import com.gjxaiou.springcloud.service.PaymentService;
import com.gjxaiou.springcloud.entities.CommonResult;
import org.springframework.stereotype.Component;


@Component
public class PaymentFallbackService implements PaymentService {
    @Override
    public CommonResult<Payment> paymentSQL(Long id) {
        return new CommonResult<>(44444, "服务降级返回,---PaymentFallbackService", new Payment(id,
                "errorSerial"));
    }
}
