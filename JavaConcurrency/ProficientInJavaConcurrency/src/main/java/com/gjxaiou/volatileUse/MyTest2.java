package com.gjxaiou.volatileUse;

import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

public class MyTest2 {
	public static void main(String[] args) {
		try {
			MyService myService = new MyService();
			IntStream.range(0, 4).forEach(i -> new Thread(
					myService::addNum).start());
			Thread.sleep(1000);
			// 获取该 atomic 值的最终值
			System.out.println(myService.atomicValue.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyService {
	public AtomicLong atomicValue = new AtomicLong();

	public void addNum() {
		// 下面两个方法每个都能保证执行的原子性，但是两个之间无法保证同步性，只能通过在方法上加 synchronized 实现
		System.out.println(Thread.currentThread().getName() + " + 100: " + atomicValue.addAndGet(100));
		System.out.println(Thread.currentThread().getName() + " + 1  : " + atomicValue.addAndGet(1));
	}
}
