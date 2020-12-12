package com.gjxaiou.springcloud.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan({"com.gjxaiou.springcloud.dao"})
public class MybatisConfig {
}
