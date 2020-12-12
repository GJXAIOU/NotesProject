package filter.demo;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/** 实现编码的过滤器
 * @author GJXAIOU
 * @create 2019-08-12-20:12
 */
public class EncodingFilter implements Filter {

    // 使用全局变量，因为所有的都要改变编码
    String encode = null;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       encode = filterConfig.getInitParameter("encoding");

    }

    /** 书写一个过滤器考虑问题：
     * 1.过滤器的范围；
     * 2.过滤器拦截之后需要做什么事情
     * 这里需求是做一个统一编码的过滤器
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //  这里设置代码的编码是写死的，可以在xml中设置，方便更改
        //        // 1.设置请求编码
        //servletRequest.setCharacterEncoding("utf-8");

        servletRequest.setCharacterEncoding(encode);

        // 2.只针对hello文件下的.jsp文件放行
        // 思路：获取当前请求路径，通过判断路径后缀的方式，判断后缀为.jsp的文件
        // 2-1: 因为ServletRequest是HttpServletRequest的父接口，先进行强转
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        // 2-2：获取当前请求完整路径
        String requestURI = httpServletRequest.getRequestURI();

        // 2-3: 判断文件结尾
        if (requestURI.endsWith(".jsp")){
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {

    }
}
