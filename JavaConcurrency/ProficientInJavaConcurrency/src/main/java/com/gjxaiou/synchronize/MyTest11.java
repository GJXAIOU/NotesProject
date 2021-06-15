package com.gjxaiou.synchronize;


public class MyTest11 {
	public static void main(String[] args) {
		MyService myService1 = new MyService();
		MyService myService2 = new MyService();
		new MyThread13(myService1).start();
		new MyThread13(myService2).start();
	}
}

class MyService {
	public void method1() {
		synchronized (MyService.class) {
			System.out.println(Thread.currentThread().getName() + " 进入方法 method1");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " 离开方法 method1");
		}
	}

	public void method2() throws InterruptedException {
		synchronized (MyService.class) {
			System.out.println(Thread.currentThread().getName() + " 进入方法 method2");
			System.out.println(Thread.currentThread().getName() + " 离开方法 method2");
		}
	}

}

// 新建两个线程
class MyThread13 extends Thread {
	MyService myService;

	MyThread13(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.method1();
	}
}

class MyThread14 extends Thread {
	MyService myService;

	MyThread14(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void run() {
		myService.method1();
	}
}
