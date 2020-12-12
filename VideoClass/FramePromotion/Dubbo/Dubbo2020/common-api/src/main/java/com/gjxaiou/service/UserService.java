package com.gjxaiou.service;

import com.gjxaiou.entity.UserAddress;

import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/12/11 16:52
 */
public interface UserService {
    /**
     * 按照用户id返回所有的收货地址
     */
    List<UserAddress> getUserAddressList(String userId);
}
