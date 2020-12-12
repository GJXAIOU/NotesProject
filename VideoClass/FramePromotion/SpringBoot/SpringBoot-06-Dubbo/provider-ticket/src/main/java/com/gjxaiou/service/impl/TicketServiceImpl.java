package com.gjxaiou.service.impl;

import com.gjxaiou.service.TicketService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

// 注入到容器中
@Component
//将服务发布出去
@DubboService
public class TicketServiceImpl implements TicketService {
    @Override
    public String buyTicket(String userName) {
        return userName + " buy a ticket";
    }
}
