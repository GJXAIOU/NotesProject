import com.gjxaiou.pointcut.AOPpointcut;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author GJXAIOU
 * @create 2019-09-23-21:30
 */
public class AOPTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        AOPpointcut pointcut = applicationContext.getBean("pointcut", AOPpointcut.class);
        pointcut.AopPointcut();
    }
}
