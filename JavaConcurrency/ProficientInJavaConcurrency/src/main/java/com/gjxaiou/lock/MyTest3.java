package com.gjxaiou.lock;

import java.util.concurrent.locks.ReentrantLock;

public class MyTest3 {
	public static void main(String[] args) {
		Service service = new Service();
		service.method1();
	}
}

class Service {
	private ReentrantLock lock = new ReentrantLock(true);

	public void method1() {
		System.out.println("A --" + lock.getHoldCount());
		lock.lock();
		System.out.println("B --" + lock.getHoldCount());
		method2();
		System.out.println("F --" + lock.getHoldCount());
		lock.unlock();
		System.out.println("G --" + lock.getHoldCount());
	}

	public void method2() {
		System.out.println("C --" + lock.getHoldCount());
		lock.lock();
		System.out.println("D --" + lock.getHoldCount());
		lock.unlock();
		System.out.println("E --" + lock.getHoldCount());
	}
}
