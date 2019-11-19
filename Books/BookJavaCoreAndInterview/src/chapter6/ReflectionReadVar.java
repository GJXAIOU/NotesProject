package chapter6;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class MyValClass {
	private int val1;
	public String val2;
	final protected String val3 = "Java";
}

public class ReflectionReadVar {
	public static void main(String[] args) {
		Class<MyValClass> clazz = MyValClass.class;
		//获取这个类的所有属性
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			//输出修饰符
			System.out.print(Modifier.toString(field.getModifiers()) + "\t");
			//输出属性的类型
			System.out.print(field.getGenericType().toString() + "\t");
			//输出属性的名字
			System.out.println(field.getName());
		}
	}
}
/**output:
 * private	int	val1
 * public	class java.lang.String	val2
 * protected final	class java.lang.String	val3
 */
