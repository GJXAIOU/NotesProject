package filter.demo;

import javax.servlet.*;
import java.io.IOException;

/**
 * 自定义类实现和Filter接口，包路径为：javax.servlet
 * @author GJXAIOU
 * @create 2019-08-12-19:36
 */

public class FirstFilter implements Filter {
    // 重写三个生命周期的方法

    /**
     * 过滤器初始化时候调用，在服务器启动之后立刻进行初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 过滤器销毁的时候执行的方法
     */
    @Override
    public void destroy() {

    }

    /**
     * 进行过滤操作，只要用请求通过该过滤器，就会执行doFilter方法,默认执行过滤（第三个参数表示放行请求）
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws
            IOException, ServletException {


    }
}

/**
 * servlet生命周期
 *
 * 1.什么时候初始化：
 * 在客户端第一次访问某一个Servlet的时候，此Servlet会初始化，并且调用init方法； 注：init在一个生命周期只会调用一次，每次访问
 * 该Servlet重复执行的是service方法
 *
 * 2.什么时候销毁：
 * 直到服务器(tomcat)关闭的时候Servlet才会销毁
 */


/**


 */