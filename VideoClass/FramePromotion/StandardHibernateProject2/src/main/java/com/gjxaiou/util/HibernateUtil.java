package com.gjxaiou.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author GJXAIOU
 * @create 2019-09-28-19:23
 */
public class HibernateUtil {
    static Configuration configuration = null;
    static SessionFactory sessionFactory = null;

    static {
        // 加载核心配置文件
        configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();

    }

    /**
     * 提供 session 与本地线程绑定的方法
     * @return 与本地线程绑定之后的 Session
     */
    public static Session getSessionObject() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 提供返回 sessionFactory 方法
     * @return sessionFactory 对象
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
