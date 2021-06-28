package com.gjxaiou.thread.state;

public class MyTest4 {
	public static void main(String[] args) {
		try {
			MyThread myThread = new MyThread();
			myThread.start();
			Thread.sleep(1000);
			System.out.println("线程状态：" + myThread.getState());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Lock {
	public static final Byte lock = new Byte("0");
}

class MyThread extends Thread {
	@Override
	public void run() {
		try {
			synchronized (Lock.lock) {
				Lock.lock.wait();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}