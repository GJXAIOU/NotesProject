package com.gjxaiou.lock;

import java.util.concurrent.locks.ReentrantLock;

public class MyTest4 {
	ReentrantLock lock = new ReentrantLock();

	// ...
	public void m() {
		assert lock.getHoldCount() == 0;
		lock.lock();
		try {
			// ... method body
		} finally {
			lock.unlock();
		}
	}
}

