package com.gjxaiou.threadLocal;

import java.util.stream.IntStream;

public class MyTest3 {
	public static void main(String[] args) {
		IntStream.range(0, 2).forEach(j -> {
			new Thread(() -> {
				try {
					for (int i = 0; i < 3; i++) {
						Tools.threadLocal.set(Thread.currentThread().getName() + " 设置：" + i);
						System.out.println(Thread.currentThread().getName() + " get  " + Tools.threadLocal.get());
						Thread.sleep((int) (Math.random() * 100));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		});

	}
}

class Tools {
	public static ThreadLocal threadLocal = new ThreadLocal();
}


