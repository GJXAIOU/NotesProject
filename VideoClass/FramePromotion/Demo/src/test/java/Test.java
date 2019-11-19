
import com.gjxaiou.pojo.People;
import com.gjxaiou.pojo.SpringLifeCycle;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author GJXAIOU
 * @create 2019-09-23-21:30
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");
        SpringLifeCycle springLifeCycle = (SpringLifeCycle) context.getBean("springLifeCycle");
        springLifeCycle.sayHello();

        // 销毁 Spring 容器
        ClassPathXmlApplicationContext classContext = (ClassPathXmlApplicationContext) context;
        classContext.close();
    }
}
