package com.gjxaiou.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author GJXAIOU
 * @Date 2020/1/6 20:51
 * <p>
 * SpringBoot 整合 Servlet 方式一：通过注解扫描完成 Servlet 组件的注册
 */


/**
 * 之前 Spring 整合的时候需要在 web.xml 中配置以下信息
 * <servlet>
 * <servlet-name>FirstServlet</servlet-name>
 * <servlet-class>com.gjxaiou.servlet.FirstServlet</servlet-class>
 * </servlet>
 * <servlet-mapping>
 * <servlet-name>FirstServlet</servlet-name>
 * <url-pattern>/first</url-pattern>
 * </servlet-mapping>
 */

// 在 springboot 中只要在类上声明即可，无需在 web.xml 中进行配置
@WebServlet(name = "FirstServlet", urlPatterns = "/first")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过访问 http://localhost:8080/first 然后看控制台输出判断是否执行
        System.out.println("FirstServlet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
