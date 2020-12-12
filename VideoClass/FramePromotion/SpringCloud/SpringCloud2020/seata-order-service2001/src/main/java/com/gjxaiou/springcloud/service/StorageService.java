package com.gjxaiou.springcloud.service;

import com.gjxaiou.springcloud.domain.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 订单微服务要调用库存微服务，通过 Feign 接口调用，即过一会要去这个微服务：seata-stroage-service 下 /storage/decrease 方法区减库存。
 *
 * @Author GJXAIOU
 * @Date 2020/12/6 14:30
 */

@FeignClient(value = "seata-stroage-service")
public interface StorageService {

    @PostMapping(value = "/storage/decrease")
    CommonResult decrease(@RequestParam("productId") Long productId,
                          @RequestParam("count") Integer count);
}
