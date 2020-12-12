package com.atguigu.principle.inversion;

/**
 * ���Person������Ϣ�Ĺ���
 * ���⣺������ǻ�ȡ�Ķ����� ΢�ţ����ŵȵȣ��������࣬ͬʱ Perons ҲҪ������Ӧ�Ľ��շ�������Ϊ person �� Email ����
 * ���˼·������һ������Ľӿ� IReceiver, ��ʾ������, ���� Person ����ӿ� IReceiver ��������
 * ��Ϊ Email, WeiXin �ȵ����ڽ��յķ�Χ�����Ǹ���ʵ�� IReceiver �ӿھ� ok, �������Ǿͷ���������תԭ��
 */
public class DependecyInversion {
	public static void main(String[] args) {
		Person person = new Person();
		person.receive(new Email());
	}
}

class Email {
	public String getInfo() {
		return "�����ʼ���Ϣ: hello,world";
	}
}

class Person {
	public void receive(Email email ) {
		System.out.println(email.getInfo());
	}
}
