package chapter6;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Person {
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public void saySkill(String skill) {
		System.out.println("Name is:" + name + ", skill is:" + skill);
	}

	public int addSalary(int current) {
		return current + 100;
	}
}

public class CallFuncDemo {
	public static void main(String[] args) {
		Class c1azz = null;
		Constructor c = null;
		try {
		// 通过反射调用类的构造函数来创建对象
			// 得到 Class 类型的对象，其中包含了 Person 类的信息
			c1azz = Class.forName("chapter6.Person");
			// 得到 Person 类带参数的构造函数；c 值为：public chapter6.Person(java.lang.String)
			c = c1azz.getDeclaredConstructor(String.class);
			// 通过带参数的构造函数创建一个Person类型对象
			Person p = (Person)c.newInstance("Peter");
			//output: Name is:Peter, skill is:java
			p.saySkill("Java");

			// 调用方法，必须传递对象实例，同时传递参数值
			Method method1 = c1azz.getMethod("saySkill", String.class);
			// 因为没返回值，所以能直接调
			// 参数一指定该方法由哪个对象调用，参数二指定该方法的参数
            method1.invoke(p, "C#");
            
            Method method2 = c1azz.getMethod("addSalary", int.class);
            Object invoke = method2.invoke(p, 100);
            //输出200
			System.out.println(invoke);
				} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException  e1) {
			e1.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
/**output:
 * Name is:Peter, skill is:Java
 * Name is:Peter, skill is:C#
 * 200
 */
