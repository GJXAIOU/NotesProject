package com.gjxaiou.volatileUse;

public class MyTest1 {
	public static void main(String[] args) {
		try {
			MyThread1 myThread1 = new MyThread1();
			myThread1.start();
			Thread.sleep(1000);
			myThread1.setRunning(false);
			System.out.println("已经设置为 false");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThread1 extends Thread {
	// private volatile boolean isRunning = true;
	private boolean isRunning = true;


	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean running) {
		isRunning = running;
	}

	@Override
	public void run() {
		String anyString = new String();
		System.out.println("进入 run 方法");
		// 里面不能有任何代码
		while (isRunning == true) {
			synchronized (anyString){
			}
		}
		System.out.println("线程被终止了");
	}
}
