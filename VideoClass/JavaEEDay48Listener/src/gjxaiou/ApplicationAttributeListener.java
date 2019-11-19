package gjxaiou;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;

/**
 * @author GJXAIOU
 * @create 2019-08-15-18:57
 */
public class ApplicationAttributeListener implements ServletContextAttributeListener {
    // 监听application对象，其中增加属性的时候触发，对应方法：setAttributes()
    @Override
    public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    // 监听application对象，其中删除属性的时候触发，对应方法：removeAttributes()
    @Override
    public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {

    }

    // 监听application对象，其中重新赋值属性，覆盖之前数据的时候触发。
    @Override
    public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {

    }
}
