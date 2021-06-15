package com.gjxaiou.thread;

public class MyTest9 {
	public static void main(String[] args) {

		try {
			MyThread9 myThread9 = new MyThread9();
			myThread9.start();
			Thread.sleep(20);
			myThread9.interrupt();
		} catch (InterruptedException e) {
			System.out.println("main catch");
			e.printStackTrace();
		}
		System.out.println("happy end");
	}
}

class MyThread9 extends Thread {
	@Override
	public void run() {
		System.out.println("开始执行 run.");
		try {
			for (int i = 0; i < 30000; i++) {
				if (this.interrupted()) {
					System.out.println("线程已经停止，要退出啦");
					throw new InterruptedException();
				}
				System.out.println("i 的值" + i);
			}
			System.out.println("不会执行 for 循环外面");
		} catch (InterruptedException e) {
			System.out.println("进入 run 的 catch 方法");
			e.printStackTrace();
		}
	}
}
