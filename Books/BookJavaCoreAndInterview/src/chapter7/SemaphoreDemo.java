import java.util.concurrent.Semaphore;

class ConnectionProvide {
	public void provide() {
		//省略提供连接对象的代码
	}
}

class HandleUserThread extends Thread {
	private Semaphore semaphore;
	private String threadName;
	private ConnectionProvide provider;

	public HandleUserThread(String threadName, Semaphore semaphore,
			ConnectionProvide provider) {
		this.semaphore = semaphore;
		this.threadName = threadName;
		this.provider = provider;
	}

	public void run() {

		if (semaphore.availablePermits() > 0) {
			System.out.println(threadName + " start，apply the connection.");
		} else {
			System.out.println(threadName + " start，no available connection.");
		}
		try {
			semaphore.acquire();
			provider.provide();
			Thread.sleep(1000);
			System.out.println(threadName + " get connection.");
			// 用数据库的连接
			semaphore.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

public class SemaphoreDemo {
	public static void main(String[] args) {
		ConnectionProvide provider = new ConnectionProvide();
        Semaphore semaphore = new Semaphore(2,true);
        for(int i=0;i<5;i++)
        {
        	new HandleUserThread(Integer.valueOf(i).toString(),semaphore,provider).start();        	
        }
	}

}
