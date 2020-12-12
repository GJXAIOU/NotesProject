package com.gjxaiou.springcloud.dao;

import com.gjxaiou.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    // 提供读取和写入方法
    Payment getPaymentById(@Param("id") Long id);

    int createPayment(Payment payment);
}
