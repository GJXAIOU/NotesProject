package com.gjxaiou.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MyTest6 {
	public static void main(String[] args) throws InterruptedException {
		ReadWriteService readWriteService = new ReadWriteService();
		// 调换两个顺序实现写读和读写
		new ReadThread(readWriteService).start();
		Thread.sleep(1000);
		new WriteThread(readWriteService).start();
	}
}

class ReadWriteService {
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	public void read() {
		try {
			try {
				lock.readLock().lock();
				System.out.println(Thread.currentThread().getName() + " acquire read lock,time = " + System.currentTimeMillis());
				Thread.sleep(5000);
			} finally {
				lock.readLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void write() {
		try {
			try {
				lock.writeLock().lock();
				System.out.println(Thread.currentThread().getName() + " acquire write lock,time " +
						"=" +
						" " + System.currentTimeMillis());
				Thread.sleep(5000);
			} finally {
				lock.writeLock().unlock();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class ReadThread extends Thread {
	private ReadWriteService readWriteService;

	ReadThread(ReadWriteService readWriteService) {
		this.readWriteService = readWriteService;
	}

	@Override
	public void run() {
		readWriteService.read();
	}
}

class WriteThread extends Thread {
	private ReadWriteService readWriteService;

	WriteThread(ReadWriteService readWriteService) {
		this.readWriteService = readWriteService;
	}

	@Override
	public void run() {
		readWriteService.write();
	}
}
