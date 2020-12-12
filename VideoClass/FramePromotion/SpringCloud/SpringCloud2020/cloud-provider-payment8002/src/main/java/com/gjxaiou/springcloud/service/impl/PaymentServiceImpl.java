package com.gjxaiou.springcloud.service.impl;

import com.gjxaiou.springcloud.dao.PaymentDao;
import com.gjxaiou.springcloud.entities.Payment;
import com.gjxaiou.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    PaymentDao paymentDao;

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public int createPayment(Payment payment) {
        return paymentDao.createPayment(payment);
    }
}
