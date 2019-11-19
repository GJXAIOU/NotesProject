package gjxaiou;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * @author GJXAIOU
 * @create 2019-08-15-18:48
 */
public class requestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    // 每个请求创建的时候就会执行，可以用作计数，和过滤器功能相似；
    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {

    }
}
