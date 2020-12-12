package com.gjxaiou.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 15:28
 */
public interface LoadBalancer {
    // 步骤一：收集 Eureka 上所有活着的机器数目
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
