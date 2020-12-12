package com.gjxaiou.springcloud.myHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gjxaiou.springcloud.entities.CommonResult;
import com.gjxaiou.springcloud.entities.Payment;

/**
 * @Author GJXAIOU
 * @Date 2020/12/5 15:52
 */
public class CustomBlockHandler {
    public static CommonResult handlerException1(BlockException exception) {
        return new CommonResult(4444, "用户自定义，global handlerException1", new Payment(2000L,
                "serial 004"));
    }
    public static CommonResult handlerException2(BlockException exception) {
        return new CommonResult(4444, "用户自定义，global handlerException2", new Payment(2000L,
                "serial 004"));
    }
}
