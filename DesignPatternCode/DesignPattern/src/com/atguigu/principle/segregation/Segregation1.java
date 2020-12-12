package com.atguigu.principle.segregation;

public class Segregation1 {
	public static void main(String[] args) {
	}
}

// �ӿ� Interface1
interface Interface1 {
	void operation1();
	void operation2();
	void operation3();
	void operation4();
	void operation5();
}

/**
 *  �� B ʵ���˽ӿ� Interface1
 */
class B implements Interface1 {
	@Override
	public void operation1() {
		System.out.println("B ʵ���� operation1");
	}
	@Override
	public void operation2() {
		System.out.println("B ʵ���� operation2");
	}
	@Override
	public void operation3() {
		System.out.println("B ʵ���� operation3");
	}
	@Override
	public void operation4() {
		System.out.println("B ʵ���� operation4");
	}
	@Override
	public void operation5() {
		System.out.println("B ʵ���� operation5");
	}
}

/**
 * �� D ʵ���˽ӿ� Interface1
 */
class D implements Interface1 {
	@Override
	public void operation1() {
		System.out.println("D ʵ���� operation1");
	}
	@Override
	public void operation2() {
		System.out.println("D ʵ���� operation2");
	}
	@Override
	public void operation3() {
		System.out.println("D ʵ���� operation3");
	}
	@Override
	public void operation4() {
		System.out.println("D ʵ���� operation4");
	}
	@Override
	public void operation5() {
		System.out.println("D ʵ���� operation5");
	}
}

/**
 * A ��ͨ���ӿ� Interface1 ����(ʹ��) B �࣬����ֻ���õ� 1,2,3 ����
 */
class A {
	public void depend1(Interface1 i) {
		i.operation1();
	}
	public void depend2(Interface1 i) {
		i.operation2();
	}
	public void depend3(Interface1 i) {
		i.operation3();
	}
}

/**
 *  C ��ͨ���ӿ� Interface1 ����(ʹ��) D �࣬����ֻ���õ� 1,4,5 ����
 */
class C {
	public void depend1(Interface1 i) {
		i.operation1();
	}
	public void depend4(Interface1 i) {
		i.operation4();
	}
	public void depend5(Interface1 i) {
		i.operation5();
	}
}