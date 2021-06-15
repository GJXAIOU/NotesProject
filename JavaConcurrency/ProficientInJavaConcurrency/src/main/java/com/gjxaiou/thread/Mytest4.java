package com.gjxaiou.thread;

public class Mytest4 {
	public void a() {
		b();
	}

	public void b() {
		c();
	}

	public void c() {
		d();
	}

	public void d() {
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		if (stackTrace != null) {
			for (StackTraceElement stackTraceElement : stackTrace) {
				System.out.println(stackTraceElement.getClassName() + " : " + stackTraceElement.getMethodName() + " " +
						": " + stackTraceElement.getLineNumber());
			}
		}

		System.out.println("当前线程的堆栈跟踪信息为：");
		Thread.dumpStack();
	}

	public static void main(String[] args) {
		Mytest4 mytest4 = new Mytest4();
		mytest4.a();
	}
}
