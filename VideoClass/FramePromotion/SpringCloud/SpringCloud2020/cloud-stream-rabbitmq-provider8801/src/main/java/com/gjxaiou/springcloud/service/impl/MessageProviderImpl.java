package com.gjxaiou.springcloud.service.impl;

import com.gjxaiou.springcloud.service.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author GJXAIOU
 * @Date 2020/12/3 19:18
 */
// 定义消息的推送管道，这里不在是 controller 调用 service 的逻辑结构，所以不需要加上 @Service
@EnableBinding(Source.class)
@Slf4j
public class MessageProviderImpl implements IMessageProvider {

    // 消息发送管道
    @Resource
    private MessageChannel output;

    // 因为构建了 message 对象，然后将其传递给管道 source，然后在传递给 MQ，不用返回值。
    @Override
    public String send() {
        String value = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(value).build());
        log.info("**** 进入 send 方法：value = " + value);
        return null;
    }
}
