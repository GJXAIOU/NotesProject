class Account {
	int balance;

	public Account() {
		balance = 0;
	}

	public synchronized void login() {
	}
	
	public synchronized void logout() {
	}
	
	public synchronized void add() {
		balance += 800;
		System.out.println("After add balance is:" + balance);
	}

	public synchronized void minus() {
		balance -= 800;
		System.out.println("After minus balance is:" + balance);
	}
}

class AddThread extends Thread {
	String person;
	
	Account acc;

	public AddThread(String person,Account acc) {
		this.person = person;
		this.acc = acc;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(person + " add money," +  i  + " cnt");
			acc.login();
			System.out.println(person + " login ");
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			acc.add();
			System.out.println(person + " logout ");
			acc.logout();
		}
	}
}

class MinusThread extends Thread {
	Account acc;
	String person;

	public MinusThread(String person,Account acc) {
		this.person = person;
		this.acc = acc;
	}

	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println(person + " minus money," + i + " cnt");
			System.out.println(person + " login ");
			acc.login();
			try {
				sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			acc.minus();
			System.out.println(person + " logout ");
			acc.logout();
		}
	}
}

public class SyncLimit {
	public static void main(String[] args) {
		Account acc = new Account();
		Thread add = new AddThread("Tom",acc);
		Thread minus = new MinusThread("Peter",acc);
		add.start();
		minus.start();
	}

}
