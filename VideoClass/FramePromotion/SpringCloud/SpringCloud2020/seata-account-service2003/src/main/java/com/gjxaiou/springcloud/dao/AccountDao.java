package com.gjxaiou.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 15:47
 */
@Mapper
public interface AccountDao {
    // 扣减账户余额
    void decrease(@Param("userId") Long userId, @Param("money")BigDecimal money);
}
