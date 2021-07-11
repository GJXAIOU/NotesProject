package com.gjxaiou.principle.liskov;

public class Liskov {
	public static void main(String[] args) {
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("1-8=" + a.func1(1, 8));
		System.out.println("-----------------------");

		B b = new B();
		//这里本意是求出11-3，但是因为方法被重写了，因此结果为 14
		System.out.println("11-3=" + b.func1(11, 3));
		System.out.println("11+3+9=" + b.func2(11, 3));
	}
}

/**
 * A类：返回两个数的差
  */
class A {
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

/**
 * B 类继承了 A 增加了一个新功能：完成两个数相加,然后和9求和
 * 同时 B 类中不小心将 A 类中的 func1() 进行了重写
  */

class B extends A {
	// 这里，重写了A类的方法, 可能是无意识
	@Override
	public int func1(int a, int b) {
		return a + b;
	}

	public int func2(int a, int b) {
		return func1(a, b) + 9;
	}
}
