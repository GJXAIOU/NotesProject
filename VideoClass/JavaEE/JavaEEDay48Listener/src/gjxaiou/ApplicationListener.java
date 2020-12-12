package gjxaiou;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author GJXAIOU
 * @create 2019-08-15-18:18
 */

public class ApplicationListener implements ServletContextListener {
    private long beginTime;
    private long endTime;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        long begin = System.currentTimeMillis();
        System.out.println("contextInitialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        endTime = System.currentTimeMillis();
        System.out.println("contextDestroyed");
        System.out.println("系统共运行了" + (endTime - beginTime) + "毫秒");
    }
}
