import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AccountWithLock {
	int balance;
	private Lock lock;

	public AccountWithLock() {
		balance = 0;
		lock = new ReentrantLock();
	}

	public void lockAccount() {
		lock.tryLock();
	}

	public void unLockAccount() {
		lock.unlock();
	}

	public void login() {

	}

	public void logout() {

	}

	public void add() {
		balance += 800;
		System.out.println("After add balance is:" + balance);
	}

	public void minus() {
		balance -= 800;
		System.out.println("After minus balance is:" + balance);
	}
}

class AddThreadWithLock extends Thread {
	String person;

	AccountWithLock acc;

	public AddThreadWithLock(String person, AccountWithLock acc) {
		this.person = person;
		this.acc = acc;
	}

	public void run() {
		acc.lockAccount();
		acc.login();
		System.out.println(person + " login ");
		for (int i = 0; i < 3; i++) {
			System.out.println(person + " add money," + i + " cnt");
			acc.add();			
		}
		System.out.println(person + " logout ");
		acc.logout();
		acc.unLockAccount();
	}
}

class MinusThreadWithLock extends Thread {
	AccountWithLock acc;
	String person;

	public MinusThreadWithLock(String person, AccountWithLock acc) {
		this.person = person;
		this.acc = acc;
	}

	public void run() {
		acc.lockAccount();
		System.out.println(person + " login ");
		for (int i = 0; i < 3; i++) {			
			System.out.println(person + " minus money," + i + " cnt");			
			acc.minus();			
		}
		System.out.println(person + " logout ");
		acc.logout();
		acc.unLockAccount();
	}
}

public class LockDemo {
	public static void main(String[] args) {
		AccountWithLock acc = new AccountWithLock();
		Thread add = new AddThreadWithLock("Tom", acc);
		Thread minus = new MinusThreadWithLock("Peter", acc);
		add.start();
		minus.start();
	}
}
