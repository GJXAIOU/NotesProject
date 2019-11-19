import com.gjxaiou.pojo.People;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author GJXAIOU
 * @create 2019-09-23-10:23
 */
public class SpringTest {
    public static void main(String[] args) {
        // 加载配置文件，同时创建对应类
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        // 使用 getBean() 创建对象
//        People people = applicationContext.getBean("people", People.class);
//        System.out.println("创建的对象名为：" + people + "\n");

        // 查看当前 Spring 容器中管理的所有类对象(以及数量)
        int beanDefinitionCount = applicationContext.getBeanDefinitionCount();
        System.out.println("当前 Spring 容器中管理的类对象数目为：" + beanDefinitionCount + "\n");
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println( "当前 Spring 容器中管理的类对象为：" + beanDefinitionName + "\n");
        }
    }
}
