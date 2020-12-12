package com.gjxaiou.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @Author GJXAIOU
 * @Date 2020/1/7 22:07
 */
public class SecondListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("SecondListener ...init....");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
