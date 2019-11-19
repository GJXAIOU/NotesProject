package reflect;

import entity.Person;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author GJXAIOU
 * @create 2019-08-10-13:56
 */
public class ReflectPractice {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        // 1.获取class类对象
        Class person = Class.forName("entity.Person");

        // 2.获取具体的构造方法
        // 获取无参构造方法
        Constructor constructor = person.getConstructor();
        // 获取带有参数的构造方法
        Constructor constructor1 = person.getConstructor(int.class, String.class);
        // 获取全部构造方法（非私有的）
        Constructor[] constructors = person.getConstructors();
        for (Constructor constructor2 : constructors) {
            System.out.println(constructor);
        }

        // 3.使用构造方法构建类对象,默认是Object类型，需要强转
        Person person1 = (Person) constructor.newInstance();
        Person person2 = (Person) constructor1.newInstance(2, "张三");

        // 4.使用成员变量和成员方法
        System.out.println(person1.getName());
        System.out.println(person2.getName());


    }
}
