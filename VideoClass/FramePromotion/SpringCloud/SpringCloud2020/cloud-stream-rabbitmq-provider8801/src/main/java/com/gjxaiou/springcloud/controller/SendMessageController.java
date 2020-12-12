package com.gjxaiou.springcloud.controller;

import com.gjxaiou.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author GJXAIOU
 * @Date 2020/12/3 19:33
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage(){
       return messageProvider.send();
    }
}
