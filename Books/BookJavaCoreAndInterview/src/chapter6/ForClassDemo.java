package chapter6;

class MyClass {
	public void print() {
		System.out.println("Java");
	}
}

public class ForClassDemo {
	public static void main(String[] args) {
		MyClass myClassObj = new MyClass();
		myClassObj.print();//输出是Java
		System.out.println("*************");

		try {
			Class<?> clazz = Class.forName("chapter6.MyClass");
			MyClass myClass = (MyClass)clazz.newInstance();
			myClass.print();//输出是Java
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
/**
 * output:
 * Java
 * *************
 * Java
 */
