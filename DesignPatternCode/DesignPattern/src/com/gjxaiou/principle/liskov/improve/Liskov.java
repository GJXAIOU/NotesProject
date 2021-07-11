package com.gjxaiou.principle.liskov.improve;

public class Liskov {
	public static void main(String[] args) {
		A a = new A();
		System.out.println("11-3=" + a.func1(11, 3));
		System.out.println("-----------------------");

		B b = new B();
		//因为B类不再继承A类，因此调用者，不会再func1是求减法
		//调用完成的功能就会很明确
		System.out.println("11+3=" + b.func1(11, 3));
		System.out.println("1+8=" + b.func1(1, 8));

		//使用组合仍然可以使用到A类相关方法
		System.out.println("11-3=" + b.func3(11, 3));
	}
}

/**
 * 创建一个更加基础的基类，然后把更加基础的方法和成员写到Base类
 */
class Base {
}

/**
 * 	A 类：返回两个数的差
 */
class A extends Base {
	public int func1(int num1, int num2) {
		return num1 - num2;
	}
}

class B extends Base {
	//如果 B 需要使用 A 类的方法,使用组合关系
	private A a = new A();

	// 我们仍然想使用 A 的方法，因为这里参数和对象名重了，因此使用 this
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
