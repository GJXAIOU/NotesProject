package com.gjxaiou.object;

import java.util.ArrayList;
import java.util.List;

public class MyTest2 {

	public static void main(String[] args) {
		try {
			Object o = new Object();
			new MyThread3(o).start();
			Thread.sleep(50);
			new MyThread4(o).start();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}


class MyThread3 extends Thread {
	public Object lock;

	MyThread3(Object lock) {
		this.lock = lock;
	}

	@Override
	public void run() {
		try {
			synchronized (lock) {
				if (MyList2.getSize() != 5) {
					System.out.println("wait begin" + System.currentTimeMillis());
					lock.wait();
					System.out.println("wait end" + System.currentTimeMillis());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

class MyThread4 extends Thread {
	private Object lock;

	MyThread4(Object lock) {
		this.lock = lock;
	}

	public void run() {
		try {
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					MyList2.add();
					if (MyList2.getSize() == 5) {
						lock.notify();
						System.out.println("发出了唤醒通知");
					}
					System.out.println("添加了 " + (i + 1) + "个元素");
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}


class MyList2 {
	private static List list = new ArrayList();

	public static void add() {
		list.add("GJXAIOU");
	}

	public static int getSize() {
		return list.size();
	}
}