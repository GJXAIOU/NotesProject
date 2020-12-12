package com.gjxaiou.springcloud.dao;

import com.gjxaiou.springcloud.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 14:11
 */
@Mapper
public interface OrderDao {
    // 1. 新建订单（下订单）
    void create(Order order);

    // 2. 修改订单状态，从 0 到 1
    void update(@Param("userId") Long userId, @Param("status") Integer status);
}
