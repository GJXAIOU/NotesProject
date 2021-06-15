package com.gjxaiou.object;


import java.util.ArrayList;
import java.util.List;


public class MyTest1 {
	public static void main(String[] args) {
		MyList list = new MyList();
		new MyThread1(list).start();
		new MyThread2(list).start();
	}

}

class MyThread1 extends Thread {
	public MyList list;

	MyThread1(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				list.add();
				System.out.println("添加了 " + (i + 1) + " 个元素");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class MyThread2 extends Thread {
	public MyList list;

	MyThread2(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		try {
			while (true) {
				if (list.getSize() == 5) {
					System.out.println("到达 5 个元素了，线程 MyThread2 退出");
					throw new InterruptedException();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class MyList {
	public volatile List list = new ArrayList();

	public void add() {
		list.add("GJXAIOU");
	}

	public int getSize() {
		return list.size();
	}
}
