package com.atguigu.principle.liskov;

public class Liskov {
	public static void main(String[] args) {
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("1-8=" + a.func1(1, 8));
		System.out.println("-----------------------");

		B b = new B();
		//���ﱾ�������11-3��������Ϊ��������д�ˣ���˽��Ϊ 14
		System.out.println("11-3=" + b.func1(11, 3));
		System.out.println("11+3+9=" + b.func2(11, 3));
	}
}

/**
 * A�ࣺ�����������Ĳ�
  */
class A {
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

/**
 * B ��̳��� A ������һ���¹��ܣ�������������,Ȼ���9���
 * ͬʱ B ���в�С�Ľ� A ���е� func1() ��������д
  */

class B extends A {
	// �����д��A��ķ���, ����������ʶ
	@Override
	public int func1(int a, int b) {
		return a + b;
	}

	public int func2(int a, int b) {
		return func1(a, b) + 9;
	}
}
