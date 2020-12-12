package com.gjxaiou.springcloud.controller;

import com.gjxaiou.springcloud.domain.CommonResult;
import com.gjxaiou.springcloud.service.AccountService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 16:24
 */
@RestController
public class AccountController {
    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decrease")
    public CommonResult decrease(@RequestParam("userId") Long userId,
                                 @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return new CommonResult(200, "扣减账户余额成功！");
    }
}
