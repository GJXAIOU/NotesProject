package com.atguigu.principle.inversion.improve;

public class DependecyInversion {
	public static void main(String[] args) {
		//客户端无需改变
		Person person = new Person();
		person.receive(new Email());
		person.receive(new WeiXin());
	}
}

/**
 * 定义接收消息统一接口
 */
interface IReceiver {
	public String getInfo();
}

class Email implements IReceiver {
	@Override
	public String getInfo() {
		return "电子邮件信息: hello,world";
	}
}

//增加微信
class WeiXin implements IReceiver {
	@Override
	public String getInfo() {
		return "微信信息: hello,ok";
	}
}

class Person {
	/**
	 *  这里我们是对接口的依赖
	 * @param receiver ：传入的参数是接口
	 */
	public void receive(IReceiver receiver ) {
		System.out.println(receiver.getInfo());
	}
}
