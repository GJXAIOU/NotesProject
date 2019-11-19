import com.gjxaiou.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author GJXAIOU
 * @create 2019-09-06-21:06
 */
public interface Test {
   public static void main(String[] args) {
      // 这里配置文件加载的时候类就被创建了
      ApplicationContext applicationContext =  new ClassPathXmlApplicationContext(
              "applicationContext.xml");
      // 使用 getBean（）创建对象
      People people = applicationContext.getBean("peo",People.class);
      System.out.println(people);

      // 查看当前 spring 容器中管理的所有类对象
      String[] names = applicationContext.getBeanDefinitionNames();
      for (String string : names) {
         System.out.println(string);
      }
   }
}
