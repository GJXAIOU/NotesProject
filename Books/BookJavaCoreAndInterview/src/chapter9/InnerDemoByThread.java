public class InnerDemoByThread {

	public static void main(String[] args) {

		// 实现runnable接口，创建10个多线程并启动
		for(int threadCnt = 0;threadCnt<10;threadCnt++)
		new Thread(new Runnable() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					//在每个线程里，输出0到4 
					System.out.println(Thread.currentThread().getName() + "--"
							+ i);
				}
			}
		}).start();//注意这里需要带分号
	}
}
