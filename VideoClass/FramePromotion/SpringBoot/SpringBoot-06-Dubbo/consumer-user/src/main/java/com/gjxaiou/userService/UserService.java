package com.gjxaiou.userService;

import com.gjxaiou.service.TicketService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

// 这个 @Service 是 Spring 的
@Service
public class UserService {
    @DubboReference
    TicketService ticketService;

    public void testMethod(String userName) {
        System.out.println("恭喜：" + ticketService.buyTicket(userName));
    }
}
