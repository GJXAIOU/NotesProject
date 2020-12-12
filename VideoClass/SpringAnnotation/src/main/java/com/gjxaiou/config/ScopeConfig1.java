package com.gjxaiou.config;

import com.gjxaiou.bean.Person;
import org.springframework.context.annotation.Bean;

/**
 * @Author GJXAIOU
 * @Date 2020/3/3 21:59
 */
public class ScopeConfig1 {
    // 加入容器中
    @Bean("person")
    public Person person() {
        return new Person("张三", 25);
    }

}


