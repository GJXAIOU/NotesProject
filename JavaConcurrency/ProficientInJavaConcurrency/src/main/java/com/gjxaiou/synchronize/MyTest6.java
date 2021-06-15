package com.gjxaiou.synchronize;

public class MyTest6 {
	public static void main(String[] args) throws InterruptedException {
		Demo demo = new Demo();
		MyThread6 myThread6 = new MyThread6(demo);
		myThread6.start();
		// 输出结果很大程度上受该值影响
		Thread.sleep(20);
		demo.getValue();
	}
}

class MyThread6 extends Thread {
	Demo demo;

	MyThread6(Demo demo) {
		this.demo = demo;
	}

	@Override
	public void run() {
		demo.setValue("B", "BB");
	}
}

class Demo {
	public String username = "A";
	public String password = "AA";

	synchronized public void setValue(String username, String password) {
		try {
			this.username = username;
			Thread.sleep(6000);
			this.password = password;

			System.out.println("setValue-currentThreadName：" + Thread.currentThread().getName() +
					"，username：" + username +
					"，password：" + password);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void getValue() {
		System.out.println("getValue-currentThreadName：" + Thread.currentThread().getName() +
				"，username：" + username +
				"，password：" + password);
	}
}
