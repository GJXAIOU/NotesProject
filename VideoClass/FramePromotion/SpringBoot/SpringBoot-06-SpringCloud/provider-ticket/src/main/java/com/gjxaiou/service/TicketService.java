package com.gjxaiou.service;

import org.springframework.stereotype.Service;

@Service
public class TicketService {
    // 比较简单就不分接口和实现类了
    public String getTicket(String userName){
        System.out.println("服务提供者：8001");
        return "恭喜：" + userName + " 买了票";
    }
}
