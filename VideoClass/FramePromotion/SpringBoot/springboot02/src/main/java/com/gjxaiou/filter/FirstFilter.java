package com.gjxaiou.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 21:08
 * <p>
 * SpringBoot 整合 Filter 方式一
 * 之前通过 web.xml 配置 Filter ：同时在下面 xml 中指定拦截 /first 路径下的 Servlet
 * <filter>
 * <filter-name>FirstFilter</filter-name>
 * <filter-class>com.gjxaiou.filter.FirstFilter</filter-class>
 * </filter>
 * <filter-mapping>
 * <filter-name>FirstFilter</filter-name>
 * <url-pattern>/first</url-pattern>
 * </filter-mapping>
 */
@WebFilter(filterName = "FirstFilter", urlPatterns = "/first")
public class FirstFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入 filter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("离开 Filter");
    }

    @Override
    public void destroy() {

    }
}
