package com.gjxaiou.thread.state;

public class MyTest2 {
	public static void main(String[] args) {
		try {
			MyThead2 myThead2 = new MyThead2();
			myThead2.start();
			Thread.sleep(1000);
			System.out.println("main 方法中的状态：" + myThead2.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThead2 extends Thread {
	@Override
	public void run() {
		try {
			System.out.println("begin sleep");
			Thread.sleep(10000);
			System.out.println("end sleep");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}