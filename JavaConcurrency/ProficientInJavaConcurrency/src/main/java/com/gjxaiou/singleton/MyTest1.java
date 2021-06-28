package com.gjxaiou.singleton;

class MyObject {
	private static final MyObject myObject = new MyObject();

	public MyObject() {
	}

	// 如果存在其他成员变量，可能出现非线程安全问题
	private static String username;
	private static String password;

	public static MyObject getInstance() {
		// 如果修改则可能不一致
		username = "从不同服务器取出值可能不一致，并赋值";
		password = "从不同服务器取出值可能不一致，并赋值";
		return myObject;
	}
}