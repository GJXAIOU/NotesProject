package gjxaiou;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author GJXAIOU
 * @create 2019-08-15-18:39
 */
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.println("sessionCreated");

    }

    // 浏览器关闭不会执行该方法，因为session并没有销毁，重写打开浏览器不能使用上次创建的session而是创建了新的session是因为
    // sessionId丢失，只有第一个session的浏览器关闭30min之后才会执行第一个session的sessionDestroy方法
    // 或者主动调用invalidate()方法销毁session
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.println("sesseionDestroyed");
    }
}
