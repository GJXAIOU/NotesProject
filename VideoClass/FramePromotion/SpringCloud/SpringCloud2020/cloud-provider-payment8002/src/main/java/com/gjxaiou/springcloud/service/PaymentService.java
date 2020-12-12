package com.gjxaiou.springcloud.service;


import com.gjxaiou.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {
    // 这里直接使用 Dao 层的，未做过多的处理
    Payment getPaymentById(@Param("id") Long id);

    int createPayment(Payment payment);
}
