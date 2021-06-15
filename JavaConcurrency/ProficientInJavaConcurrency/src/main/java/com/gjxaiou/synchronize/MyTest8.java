package com.gjxaiou.synchronize;

public class MyTest8 {
	public static void main(String[] args) {
		Task task = new Task();
		new MyThread1(task).start();
		new MyThread2(task).start();
	}
}

// 任务执行逻辑
class Task {
	public void doLongThing() {
		for (int i = 0; i < 100; i++) {
			System.out.println("noSynchronizedPart: " + Thread.currentThread().getName() + " i: " + i);
		}

		synchronized (this) {
			for (int i = 0; i < 100; i++) {
				System.out.println("SynchronizedPart: " + Thread.currentThread().getName() + " " +
						"i:" +
						" " + i);
			}
		}
	}
}

// 创建两个线程
class MyThread1 extends Thread {
	Task task = new Task();

	MyThread1(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.doLongThing();
	}
}

class MyThread2 extends Thread {
	Task task = new Task();

	MyThread2(Task task) {
		this.task = task;
	}

	@Override
	public void run() {
		task.doLongThing();
	}
}
