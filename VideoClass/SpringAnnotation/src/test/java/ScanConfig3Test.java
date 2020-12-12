
import com.gjxaiou.config.ScanConfig3;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.*;

/**
 * @Author GJXAIOU
 * @Date 2020/3/3 21:27
 */
public class ScanConfig3Test {
    @Test
    public void test() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(ScanConfig3.class);
        String[] names = applicationContext.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
