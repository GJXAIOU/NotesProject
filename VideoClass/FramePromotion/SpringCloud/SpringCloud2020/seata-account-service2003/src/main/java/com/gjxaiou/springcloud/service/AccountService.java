package com.gjxaiou.springcloud.service;

import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 15:49
 */
public interface AccountService {

    void  decrease(@RequestParam("userId") Long userId, @RequestParam("money")BigDecimal money);
}
