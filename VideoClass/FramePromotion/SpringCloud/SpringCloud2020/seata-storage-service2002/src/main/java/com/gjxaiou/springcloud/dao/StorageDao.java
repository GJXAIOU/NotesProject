package com.gjxaiou.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 15:23
 */
@Mapper
public interface StorageDao {
    // 扣减库存
    void decrease(@Param("productId") Long productId, @Param("count") Integer count);
}
