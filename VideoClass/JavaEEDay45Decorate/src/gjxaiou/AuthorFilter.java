package gjxaiou;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author GJXAIOU
 * @create 2019-08-12-21:25
 */
public class AuthorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest= (HttpServletRequest)servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("islogin") == null){
            // 没有登录，跳转回登录界面重新登录
            // 参数里面的相对路径：相对于过滤器当前处理的请求的路径，当前为 main/*
            httpServletResponse.sendRedirect("../login.jsp");
        }else {
            // 用户登录，放行
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
