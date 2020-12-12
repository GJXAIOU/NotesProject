package com.gjxaiou.springcloud.controller;


import com.gjxaiou.springcloud.entities.CommonResult;
import com.gjxaiou.springcloud.entities.Payment;
import com.gjxaiou.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;


    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("根据 ID：" + id + " 查询结果为 " + payment);
        return null == payment ? new CommonResult<Payment>(400, "查询结果为空", null) :
                new CommonResult<Payment>(200, "查询成功，调用的服务提供者为：" + serverPort, payment);
    }

    @PostMapping(value = "/payment/create")
    public CommonResult insertPayment(@RequestBody Payment payment) {
        int result = paymentService.createPayment(payment);
        log.info("插入成功，插入数据为： " + payment);
        return null == payment ? new CommonResult<Integer>(400, "查询结果为空", result) :
                new CommonResult<Integer>(200, "查询成功，调用的服务提供者为：" + serverPort, result);
    }

    /**
     * 用于主动的暴露给外面自己微服务的信息
     */

    @Resource
    DiscoveryClient discoveryClient;

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        // 获取有哪些服务
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("** service **:" + service);
        }

        // 获取指定服务名称下面的具体有哪些实例
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

    /**
     * Ribbon：手写的负载均衡算法，作用：调用 8001 的时候返回该服务
     */
    @GetMapping(value = "/payment/lb")
    public String getPaymentLB() {
        return serverPort;
    }
}
