package web;

import javax.servlet.*;
import java.io.IOException;

/** 使用过滤器，只有登录之后才可以访问其他页面
 * @author GJXAIOU
 * @create 2019-08-23-14:05
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void destroy() {

    }
}
