package com.gjxaiou.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author GJXAIOU
 * @Date 2020/11/30 15:31
 */
@Component
public class LoadBalancerImpl implements LoadBalancer {

    // 步骤一：类比 RoundRibbonRule 中的 CAS，获取当前与下一个机器
    private AtomicInteger count = new AtomicInteger(0);

    public final int getAndIncrement() {
        int current;
        int next;
        do {
            current = this.count.get();
            next = current >= Integer.MAX_VALUE ? 0 : current + 1;
        } while (!this.count.compareAndSet(current, next));
        System.out.println("当前 next 值为：" + next);
        return next;
    }

    @Override
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = getAndIncrement() % serviceInstances.size();
        return serviceInstances.get(index);
    }
}
