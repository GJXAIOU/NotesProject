package com.gjxaiou.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 自定义类，同时实现 HandlerInterceptor，同时添加未实现的方法
 * @author GJXAIOU
 * @create 2019-09-20-19:34
 */
public class InterceptorDemo implements HandlerInterceptor {
    /**
     * 该方法在进入控制器之前执行
     * 所有的控制代码都写在这里，什么情况下可以访问路径，什么情况下不能访问路径；
     * @param request
     * @param response
     * @param handler
     * @return true 表示进入拦截器，false 表示阻止进入拦截器
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 参数 Object handler 表示拦截器拦截的方法的全称
        System.out.println("拦截的方法为：handeler = " + handler);
        System.out.println("preHandle");
        return true;
    }

    /**
     * 该方法在控制器执行完毕，进入到 JSP 之前执行
     * 作用：可以用于日志记录以及敏感词语过滤
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 参数 ModelAndView modelAndView 可以取到视图的值，以及取到或修改视图中参数名；
        System.out.println("往视图名：" + modelAndView.getViewName() + "跳转；");
        // 取出视图中参数值，因为 getModel() 返回值为 Map，因此使用 .get(key值)取到对应值；
        String modelString = modelAndView.getModel().get("model").toString();
        System.out.println(modelAndView.getModel().put("model", "修改后的值").toString());
        // 替换视图中参数值
        String modelStringReplace = modelString.replace("值", "替换的值");
        modelAndView.getModel().put("model", modelStringReplace);

        System.out.println("postHandle");
    }

    /**
     * 该方法在 JSP 执行完成之后执行
     * 作用：用于记录执行过程中出现的异常，并且可以将异常日志记录到日志中；
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 参数 Exception ex 值为 NULL 表示没有异常，反之有异常；
        System.out.println("是否有异常：ex = " + ex + "   异常信息为：" + ex.getMessage());
        System.out.println("afterCompletion");
    }
}
