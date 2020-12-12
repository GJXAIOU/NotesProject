package com.gjxaiou;

import com.gjxaiou.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author GJXAIOU
 * @Date 2020/1/6 21:01
 */
@SpringBootApplication
public class ServletStart2 {
    public static void main(String[] args) {
        SpringApplication.run(ServletStart2.class, args);
    }

    // 通过 ServletRegistrationBean 方法来注册 Servlet
    // 该方法返回值固定，方法名随便
    @Bean
    public ServletRegistrationBean getServletRegistrationBean() {
        ServletRegistrationBean<SecondServlet> secondServletServletRegistrationBean =
                new ServletRegistrationBean<>(new SecondServlet());
        // 配置 UrlMapping
        secondServletServletRegistrationBean.addUrlMappings("/second");
        return secondServletServletRegistrationBean;
    }
}
