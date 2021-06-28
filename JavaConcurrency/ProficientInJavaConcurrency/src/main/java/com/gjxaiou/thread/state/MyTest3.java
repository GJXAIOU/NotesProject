package com.gjxaiou.thread.state;

public class MyTest3 {
	public static void main(String[] args) {
		try {
			new Thread(() -> {
				MyService.serviceMethod();
			}).start();
			Thread.sleep(1000);
			Thread thread = new Thread(() -> {
				MyService.serviceMethod();
			});
			thread.start();
			Thread.sleep(1000);
			System.out.println("后进入的线程的状态：" + thread.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyService {
	synchronized public static void serviceMethod() {
		try {
			System.out.println(Thread.currentThread().getName() + " 进入业务方法。");
			// sleep() 并不会释放锁
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}