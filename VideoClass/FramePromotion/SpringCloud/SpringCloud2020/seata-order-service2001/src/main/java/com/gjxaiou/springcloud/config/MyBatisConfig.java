package com.gjxaiou.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author GJXAIOU
 * @Date 2020/12/6 15:04
 */
@Configuration
@MapperScan("com.gjxaiou.springcloud.dao")
public class MyBatisConfig {
}
