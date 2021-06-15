package com.gjxaiou.thread;

import java.util.Iterator;
import java.util.Map;

public class MyTest5 {
	public static void main(String[] args) {
		Test test = new Test();
		test.a();
	}
}

class Test {
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
		Map<Thread, StackTraceElement[]> allStackTraces =
				Thread.currentThread().getAllStackTraces();
		if (allStackTraces != null && allStackTraces.size() != 0) {
			Iterator<Thread> iterator = allStackTraces.keySet().iterator();
			while (iterator.hasNext()) {
				Thread next = iterator.next();
				StackTraceElement[] stackTraceElements = allStackTraces.get(next);
				System.out.println("每个线程的基本信息：" + "线程名称：" + next.getName() + " 线程状态：" + next.getState());
				if (stackTraceElements.length != 0) {
					System.out.println("输出 stackTraceElement[] 数组具体信息：");
					for (StackTraceElement stackTraceElement : stackTraceElements) {
						System.out.println(stackTraceElement.getClassName() + "   " + stackTraceElement.getMethodName() + "  " + stackTraceElement.getLineNumber());
					}
				} else {
					System.out.println("stackTraceElement[] 为空，因为线程" + next.getName() + " 中的 " +
							"StackTraceElement " +
							"数组长度为 0");
				}
			}
		}
	}
}
