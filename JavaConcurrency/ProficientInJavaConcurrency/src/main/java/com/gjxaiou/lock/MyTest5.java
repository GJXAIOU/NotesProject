package com.gjxaiou.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest5 {
	public static void main(String[] args) {
		WriteService writeService = new WriteService();
		new WriteThread1(writeService).start();
		new WriteThread1(writeService).start();
	}
}

class WriteService {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println(Thread.currentThread().getName() + " acquire the lock,time = " + System.currentTimeMillis());
				Thread.sleep(5000);
			} finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class WriteThread1 extends Thread {
	WriteService writeService ;

	WriteThread1(WriteService writeService) {
		this.writeService = writeService;
	}

	@Override
	public void run() {
		writeService.write();
	}
}