package com.gjxaiou.threadLocal;

import java.util.Date;

public class MyTest5 {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("   在Main线程中取值=" + ToolUtil.threadLocal.get());
				Thread.sleep(100);
			}
			Thread.sleep(5000);
			new ThreadA().start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class InheritableThreadLocalExt extends InheritableThreadLocal {

	@Override
	protected Object initialValue() {
		return (new Date()).getTime();
	}

	@Override
	protected Object childValue(Object parentValue) {
		return parentValue + " 我在子线程加入的";
	}
}

class ToolUtil {
	public static InheritableThreadLocalExt threadLocal = new InheritableThreadLocalExt();
}

class ThreadA extends Thread {
	public void run() {
		try {
			for (int i = 0; i < 10; i++) {
				System.out.println("在ThreadA线程中取值=" + ToolUtil.threadLocal.get());
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
