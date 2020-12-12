package com.gjxaiou.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.gjxaiou.springcloud.entities.CommonResult;
import com.gjxaiou.springcloud.entities.Payment;
import com.gjxaiou.springcloud.myHandler.CustomBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author GJXAIOU
 * @Date 2020/12/5 15:13
 */
@RestController
public class RateLimitController {

    /**
     * 测试使用资源名称进行限流
     *
     * @return
     */
    @GetMapping("/byResource")
    @SentinelResource(value = "byResource", blockHandler = "handleException")
    public CommonResult byResource() {
        return new CommonResult(200, "按照自愿名称限流测试", new Payment(2020L, "serial001"));
    }

    public CommonResult handleException(BlockException e) {
        return new CommonResult(444, e.getClass().getCanonicalName() + " 服务不可用");
    }


    /**
     * 测试使用 URL 进行限流
     */
    @GetMapping("/rateLimit/byUrl")
    @SentinelResource(value = "byUrl")
    public CommonResult byUrl() {
        return new CommonResult(200, "按照  URL 限流测试", new Payment(2020L, " serial002"));
    }


    /**
     * 测试自定义限流异常方法
     */
    @GetMapping("/rateLimit/customerBlockHandler")
    @SentinelResource(value = "customerBlockerHandler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "handlerException2")
    public CommonResult customerBlockHandler() {
        return new CommonResult(200, "按照用户自定义", new Payment(2020L, "serial003"));
    }
}
