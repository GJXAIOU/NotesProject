package com.gjxaiou.threadLocal;

public class MyTest4 {
	public static void main(String[] args) {
		try {
			for (int i = 0; i < 2; i++) {
				if (Tool.threadLocal.get() == null || Tool.inheritableThreadLocal.get() == null) {
					Tool.threadLocal.set("main 线程设置的值");
					Tool.inheritableThreadLocal.set("main 线程设置的值");
				}
				System.out.println("main 线程取值, TH: " + Tool.threadLocal.get() + "  inTH: " + Tool.inheritableThreadLocal.get());
				Thread.sleep(100);
			}

			new Thread(() -> {
				try {
					for (int i = 0; i < 2; i++) {
						System.out.println("子线程取值，TH： " + Tool.threadLocal.get() + " inTH: " + Tool.inheritableThreadLocal.get());
						Thread.sleep(100);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}

class Tool {
	public static ThreadLocal threadLocal = new ThreadLocal();
	public static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();
}


