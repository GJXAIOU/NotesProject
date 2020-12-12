package com.gjxaiou.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    // 因为 application.yml 中所有关于数据源的属性配置都是以 spring.datasource 开头，将这些属性与 DruidDataSource 中属性对应。
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    // 配置 Druid 的监控
    // 1、配置一个管理后台的 Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid" +
                "/*");
        // 可以设置以下默认属性，属性名称在 ResourceServlet 类中有说明。
        Map<String, String> initParams = new HashMap<>();

        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "GJXAIOU");
        initParams.put("allow", "");//默认就是允许所有访问
        initParams.put("deny", "localhost");

        bean.setInitParameters(initParams);
        return bean;
    }


    // 2、配置一个 web 监控的 filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }
}
