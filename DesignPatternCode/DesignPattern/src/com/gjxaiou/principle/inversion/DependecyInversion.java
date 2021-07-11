package com.gjxaiou.principle.inversion;

/**
 * 完成Person接收消息的功能
 * 问题：如果我们获取的对象是 微信，短信等等，则新增类，同时 Perons 也要增加相应的接收方法，因为 person 和 Email 绑定了
 * 解决思路：引入一个抽象的接口 IReceiver, 表示接收者, 这样 Person 类与接口 IReceiver 发生依赖
 * 因为 Email, WeiXin 等等属于接收的范围，他们各自实现 IReceiver 接口就 ok, 这样我们就符合依赖倒转原则
 */
public class DependecyInversion {
	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());
	}
}

class Email {
	public String getInfo() {
		return "电子邮件信息: hello,world";
	}
}

class Person {
	public void receive(Email email ) {
		System.out.println(email.getInfo());
	}
}
