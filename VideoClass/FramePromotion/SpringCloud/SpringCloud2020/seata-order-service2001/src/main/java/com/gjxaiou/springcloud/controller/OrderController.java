package com.gjxaiou.springcloud.controller;

import com.gjxaiou.springcloud.domain.CommonResult;
import com.gjxaiou.springcloud.domain.Order;
import com.gjxaiou.springcloud.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 15:01
 */
@RestController
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult create(Order order) {
        orderService.create(order);
        return new CommonResult(200, "订单创建成功");
    }
}
