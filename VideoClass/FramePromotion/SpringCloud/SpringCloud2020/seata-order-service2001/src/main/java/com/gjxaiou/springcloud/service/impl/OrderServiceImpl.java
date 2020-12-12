package com.gjxaiou.springcloud.service.impl;

import com.gjxaiou.springcloud.dao.OrderDao;
import com.gjxaiou.springcloud.domain.Order;
import com.gjxaiou.springcloud.service.AccountService;
import com.gjxaiou.springcloud.service.OrderService;
import com.gjxaiou.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 14:31
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService storageService;
    @Resource
    private AccountService accountService;

    @Override
    public void create(Order order) {
        // 1.新建订单（下订单）
        log.info("------> 开始新建订单---");
        orderDao.create(order);

        // 2.扣库存
        log.info("------> 订单微服务开始调用库存，扣减 count 操作开始");
        storageService.decrease(order.getProductId(), order.getCount());
        log.info("------> 订单微服务开始调用库存，扣减 count 操作完成");

        // 3.减金额
        log.info("------> 订单微服务开始调用账户，扣减 money 操作开始");
        accountService.decrease(order.getUserId(), order.getMoney());
        log.info("------> 订单微服务开始调用账户，扣减 money 操作结束");

        // 4.修改订单状态
        log.info("-------> 开始修改订单状态");
        orderDao.update(order.getUserId(), 0);
        log.info("-------> 结束修改订单状态");

        log.info("-------> 整个下单过程完成");
    }
}
