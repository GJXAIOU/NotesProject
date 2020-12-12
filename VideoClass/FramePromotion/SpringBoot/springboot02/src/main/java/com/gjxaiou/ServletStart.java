package com.gjxaiou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @Author GJXAIOU
 * @Date 2020/1/6 21:01
 */
@SpringBootApplication
// 在 SpringBoot 启动时候会扫描 @WebServlet 注解，并将该类实例化
@ServletComponentScan
public class ServletStart {
    public static void main(String[] args) {
        SpringApplication.run(ServletStart.class, args);
    }
}
