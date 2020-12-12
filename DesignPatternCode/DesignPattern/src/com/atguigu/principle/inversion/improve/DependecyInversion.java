package com.atguigu.principle.inversion.improve;

public class DependecyInversion {
	public static void main(String[] args) {
		//�ͻ�������ı�
		Person person = new Person();
		person.receive(new Email());
		person.receive(new WeiXin());
	}
}

/**
 * ���������Ϣͳһ�ӿ�
 */
interface IReceiver {
	public String getInfo();
}

class Email implements IReceiver {
	@Override
	public String getInfo() {
		return "�����ʼ���Ϣ: hello,world";
	}
}

//����΢��
class WeiXin implements IReceiver {
	@Override
	public String getInfo() {
		return "΢����Ϣ: hello,ok";
	}
}

class Person {
	/**
	 *  ���������ǶԽӿڵ�����
	 * @param receiver ������Ĳ����ǽӿ�
	 */
	public void receive(IReceiver receiver ) {
		System.out.println(receiver.getInfo());
	}
}
