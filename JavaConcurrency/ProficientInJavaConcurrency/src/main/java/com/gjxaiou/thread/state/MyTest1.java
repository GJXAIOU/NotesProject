package com.gjxaiou.thread.state;

public class MyTest1 {
	public static void main(String[] args) {
		try {
			MyThead1 myThead1 = new MyThead1();
			System.out.println("main 方法中的状态：" + myThead1.getState());
			Thread.sleep(1000);
			myThead1.start();
			Thread.sleep(1000);
			System.out.println("main 方法中的状态：" + myThead1.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThead1 extends Thread {
	MyThead1() {
		System.out.println("构造方法里面的状态：线程 " + Thread.currentThread().getName() + " 的状态为： " + Thread.currentThread().getState());
		System.out.println("构造方法里面的状态： this.getState() = " + this.getState());
	}

	@Override
	public void run() {
		System.out.println("run 方法中的状态：" + Thread.currentThread().getState());
	}
}
