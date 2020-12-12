//import com.gjxaiou.bean.Person;
//
//import com.gjxaiou.config.AddConfig1;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
///**
// * @Author GJXAIOU
// * @Date 2020/3/3 13:33
// */
//public class AddConfigTest {
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context =
//                new AnnotationConfigApplicationContext(AddConfig1.class);
//        Person person = (Person) context.getBean(Person.class);
//        System.out.println(person);
//
//        String[] names = context.getBeanNamesForType(Person.class);
//        for (String name : names) {
//            System.out.println(name);
//        }
//    }
//}
