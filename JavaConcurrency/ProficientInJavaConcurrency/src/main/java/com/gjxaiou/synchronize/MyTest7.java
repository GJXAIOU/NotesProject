package com.gjxaiou.synchronize;

public class MyTest7 {
	public static void main(String[] args) {
		new MyThread7().start();
	}
}

class MyThread7 extends Thread {
	@Override
	public void run() {
		new Service().service1();
	}
}

class Service {
	synchronized public void service1() {
		System.out.println("调用 service1 成功，尝试调用 service2");
		service2();
	}

	synchronized public void service2() {
		System.out.println("调用 service2 成功，尝试调用 service3");
		service3();
	}

	synchronized public void service3() {
		System.out.println("调用 service3 成功");
	}
}
