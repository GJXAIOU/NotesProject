import java.util.concurrent.locks.ReentrantLock;

class LockReEnter implements Runnable {

	ReentrantLock lock = new ReentrantLock();

	public void get() {
		lock.lock();
		System.out.print(Thread.currentThread().getId() + "\t");
		// 在get方法里调用set
		set();
		lock.unlock();

	}

	public void set() {
		lock.lock();
		System.out.print(Thread.currentThread().getId() + "\t");
		lock.unlock();
	}

	public void run() {
		get();
	}
}

public class ReEnterLock {

	public static void main(String[] args) {
		LockReEnter demo = new LockReEnter();
		new Thread(demo).start();
		new Thread(demo).start();

	}

}
