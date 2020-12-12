package com.gjxaiou.service;

import com.gjxaiou.entity.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/12/11 17:42
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    UserService userService;

    @Override
    public void initOrder(String userID) {

        // 1.使用 dubbo 实现远程调用提供者的 getUserAddressList 方法
        List<UserAddress> userAddressList = userService.getUserAddressList(userID);
        userAddressList.forEach(System.out::println);
    }
}
