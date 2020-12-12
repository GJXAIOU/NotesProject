package com.gjxaiou.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.asn1.mozilla.PublicKeyAndChallenge;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 9:59
 */
@RestController
@Slf4j
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul() {
        return "springCloud with consul : " + serverPort + "\t" + UUID.randomUUID().toString();
    }
}
