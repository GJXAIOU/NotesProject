package com.atguigu.principle.liskov.improve;

public class Liskov {
	public static void main(String[] args) {
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("-----------------------");

		B b = new B();
		//��ΪB�಻�ټ̳�A�࣬��˵����ߣ�������func1�������
		//������ɵĹ��ܾͻ����ȷ
		System.out.println("11+3=" + b.func1(11, 3));
		System.out.println("1+8=" + b.func1(1, 8));

		//ʹ�������Ȼ����ʹ�õ�A����ط���
		System.out.println("11-3=" + b.func3(11, 3));
	}
}

/**
 * ����һ�����ӻ����Ļ��࣬Ȼ��Ѹ��ӻ����ķ����ͳ�Աд��Base��
 */
class Base {
}

/**
 * 	A �ࣺ�����������Ĳ�
 */
class A extends Base {
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

class B extends Base {
	//��� B ��Ҫʹ�� A ��ķ���,ʹ����Ϲ�ϵ
	private A a = new A();

	// ������Ȼ��ʹ�� A �ķ�������Ϊ��������Ͷ��������ˣ����ʹ�� this
	public int func3(int a, int b) {
		return this.a.func1(a, b);
	}

	public int func1(int a, int b) {
		return a + b;
	}

	public int func2(int a, int b) {
		return func1(a, b) + 9;
	}
}
