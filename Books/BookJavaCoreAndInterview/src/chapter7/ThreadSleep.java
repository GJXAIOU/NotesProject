package chapter7;

public class ThreadSleep extends Thread {
	@Override
	public void run() {
		Long curTime = System.currentTimeMillis();
		// sleep方法会抛出InterruptedException异常
		// 需要用try-catch语句进行捕捉
		try {
			sleep(2000);
		} catch (InterruptedException e) {
		}
		System.out.println("ts线程阻塞的时间" + (System.currentTimeMillis() - curTime) + "毫秒");
	}

	public static void main(String arg[]) {
		ThreadSleep ts = new ThreadSleep();
		ts.start();
		Long curTime = System.currentTimeMillis();
        try {  
            Thread.sleep(1000);  
        } catch (InterruptedException e) {
            e.printStackTrace();  
        }  
        System.out.println("主线程阻塞的时间" + (System.currentTimeMillis() - curTime) + "毫秒");
	}
}
/**
 * output:
 * 主线程阻塞的时间1000毫秒
 * ts线程阻塞的时间2001毫秒
 */
