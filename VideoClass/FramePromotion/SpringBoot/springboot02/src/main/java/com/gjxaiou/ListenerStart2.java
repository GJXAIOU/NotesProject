package com.gjxaiou;

import com.gjxaiou.listener.SecondListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 22:08
 */
@SpringBootApplication
public class ListenerStart2 {
    public static void main(String[] args) {
        SpringApplication.run(ListenerStart2.class, args);
    }

    /**
     * 注册 Listener
     */
    @Bean
    public ServletListenerRegistrationBean<SecondListener> getServletListenerRegistrationBean() {
        ServletListenerRegistrationBean<SecondListener> bean =
                new ServletListenerRegistrationBean<SecondListener>(new SecondListener());
        return bean;
    }

}
