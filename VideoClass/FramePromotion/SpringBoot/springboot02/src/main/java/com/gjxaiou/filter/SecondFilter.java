package com.gjxaiou.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 21:28
 * SpringBoot 注册 Filter 方式二:
 */
public class SecondFilter implements Filter {
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
