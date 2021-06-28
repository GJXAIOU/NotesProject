package com.gjxaiou.singleton;

import java.io.*;

public class MyTest {
	public static void main(String[] args) {

		// 序列化
		try {
			MySingletonObject instance = MySingletonObject.getInstance();
			System.out.println("序列化 instance = " + instance.hashCode() + " UserInfo = " + instance.userInfo.hashCode());
			FileOutputStream fosRef = new FileOutputStream(new File("MySingletonObject" +
					"-File.txt"));
			ObjectOutputStream oosRef = new ObjectOutputStream(fosRef);
			oosRef.writeObject(instance);
			oosRef.close();
			fosRef.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 反序列化
		try {
			FileInputStream fisRef =
					new FileInputStream(new File("MySingletonObject-File.txt"));
			ObjectInputStream iosRef = new ObjectInputStream(fisRef);
			MySingletonObject getInstance = (MySingletonObject) iosRef.readObject();
			iosRef.close();
			fisRef.close();
			System.out.println("序列化 instance = " + getInstance.hashCode() + " UserInfo = " + getInstance.userInfo.hashCode());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}

class MySingletonObject implements Serializable {
	private static final long serialVersionUID = 2233L;

	public static UserInfo userInfo = new UserInfo();
	private static MySingletonObject singletonObject = new MySingletonObject();

	private MySingletonObject() {

	}

	public static MySingletonObject getInstance() {
		return singletonObject;
	}

	protected Object readResolve() throws ObjectStreamException {
		System.out.println("调用了 readResolve() 方法");
		return MySingletonObject.singletonObject;
	}

}

class UserInfo {
	private int age;
	private String name;
}