package com.gjxiaou.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

/**
 * @Author GJXAIOU
 * @Date 2020/12/1 19:04
 */
@Component
@Slf4j
public class MyLogGatewayFilter implements GlobalFilter, Ordered {
    // 过滤规则：访问必须带着某个用户名
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入 Gateway 的 Filter 中......" + new Date());
        String userName = exchange.getRequest().getQueryParams().getFirst("uname");
        if (userName == null) {
            log.info("用户名为 null，非法用户........");
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
        // 通过则放行
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        // 加载该过滤器的优先级，值越小越优先。
        return 0;
    }
}
