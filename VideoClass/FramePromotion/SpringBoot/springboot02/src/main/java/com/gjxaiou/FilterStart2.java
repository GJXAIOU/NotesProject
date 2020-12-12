package com.gjxaiou;

import com.gjxaiou.filter.SecondFilter;
import com.gjxaiou.servlet.SecondServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 21:48
 */
@SpringBootApplication
public class FilterStart2 {
    public static void main(String[] args) {
        SpringApplication.run(FilterStart2.class, args);
    }

    // 通过 ServletRegistrationBean 方法来注册 Servlet
    // 该方法返回值固定，方法名随便
    @Bean
    public ServletRegistrationBean getServletRegistrationBean2() {
        ServletRegistrationBean<SecondServlet> secondServletServletRegistrationBean =
                new ServletRegistrationBean<>(new SecondServlet());
        // 配置 UrlMapping
        secondServletServletRegistrationBean.addUrlMappings("/second");
        return secondServletServletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean getFilterRegistrationBean() {
        FilterRegistrationBean<SecondFilter> secondFilterFilterRegistrationBean =
                new FilterRegistrationBean<>(new SecondFilter());
        // 可以拦截多个 URL
        //secondFilterFilterRegistrationBean.addUrlPatterns(new String[]{"*.do", "*.jsp"});
        secondFilterFilterRegistrationBean.addUrlPatterns("/second");
        return secondFilterFilterRegistrationBean;
    }
}
