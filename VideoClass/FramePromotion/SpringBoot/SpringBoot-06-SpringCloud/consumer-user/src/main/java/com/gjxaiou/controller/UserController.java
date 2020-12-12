package com.gjxaiou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/buyticket")
    public String buyTicket(String userName) {
        // URL 为 服务名称/地址
        String s = restTemplate.getForObject("http://PROVIDER-TICKET/ticket", String.class);
        return userName + s;
    }
}
