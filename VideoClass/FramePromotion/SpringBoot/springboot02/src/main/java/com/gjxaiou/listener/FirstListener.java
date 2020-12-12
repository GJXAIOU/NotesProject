package com.gjxaiou.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 21:58
 * <p>
 * SpringBoot 整合 Listener 方式一：
 * 之前在 web.xml 中配置方式为：
 * <listener>
 * <listener-class>com.gjxaiou.listener.FirstListener</listener-class>
 * </listener>
 */

// 具体实现什么接口根据 Listener 作用而定
@WebListener
public class FirstListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Listener ...init....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
