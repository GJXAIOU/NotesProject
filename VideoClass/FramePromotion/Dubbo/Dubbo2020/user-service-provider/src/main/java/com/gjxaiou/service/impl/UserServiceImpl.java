package com.gjxaiou.service.impl;

import com.gjxaiou.entity.UserAddress;
import com.gjxaiou.service.UserService;

import java.util.Arrays;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/12/11 17:32
 */
public class UserServiceImpl implements UserService {
    @Override
    public List<UserAddress> getUserAddressList(String userId) {

        UserAddress address1 = new UserAddress(1, "河南省郑州巩义市宋陵大厦2F", "1", "安然", "150360313x", "Y");
        UserAddress address2 = new UserAddress(2, "北京市昌平区沙河镇沙阳路", "1", "情话", "1766666395x", "N");

        return Arrays.asList(address1, address2);
    }
}
