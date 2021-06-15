package com.gjxaiou.synchronize;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MyTest9 {
	public static void main(String[] args) {
		MyList list = new MyList();
		MyThread9 myThread9 = new MyThread9(list);
		myThread9.setName("A");
		myThread9.start();
		MyThread10 myThread10 = new MyThread10(list);
		myThread10.setName("B");
		myThread10.start();
	}
}

class MyList {
	public List list = new ArrayList();

	synchronized public void add(String name) {
		System.out.println(Thread.currentThread().getName() + "执行了当前方法");
		list.add(name);
		System.out.println(Thread.currentThread().getName() + "退出了当前方法");
	}
}

// 创建两个线程类
class MyThread9 extends Thread {
	private MyList list;

	MyThread9(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			list.add("thread A : " + (i + 1));
		}
	}
}

class MyThread10 extends Thread {
	private MyList list;

	MyThread10(MyList list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			list.add("thread B : " + (i + 1));
		}
	}
}