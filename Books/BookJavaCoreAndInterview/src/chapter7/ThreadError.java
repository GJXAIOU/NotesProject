package chapter7;

//需要同步的对象类
class SynObject {
	// 定义两个属性
	int i;
	int j;

	// 把两个属性同时加1
     public synchronized  void add() {
	//public void add() {
		i++;
		// 睡眠5000毫秒
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		j++;
		// 打印当前i,j的值
		System.out.println("Operator：+  Data：i=" + i + ",j=" + j);
	}

	// 把两个属性同时减1
	//public  void minus() { 
	public  synchronized  void minus() {
		i--;
		// 睡眠500毫秒
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		j--;
		// 打印当前i,j的值
		System.out.println("Operator：-  Data：i=" + i + ",j=" + j);
	}
}

class SynThreadAdd extends Thread {
	// 需要同步的对象
	SynObject o;
	// 接受需要操作的那个对象的代参构造函数
	public SynThreadAdd(SynObject o) {
		this.o = o;
	}
	// 覆写线程对象的run方法定义真正的执行逻辑
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			o.add();
		}
	}
}

class SynThreadMinus extends Thread {
	// 需要同步的对象
	SynObject o;
	// 接受需要操作的那个对象的代参构造函数
	public SynThreadMinus(SynObject o) {
		this.o = o;
	}

	// 覆写线程对象的run方法定义真正的执行逻辑
	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			o.minus();
		}
	}
}

public class ThreadError {
	public static void main(String args[]) {
		// 实例化需要同步的对象
		SynObject o = new SynObject();
		// 实例化两个并行操作该同步对象的线程
		Thread t1 = new SynThreadAdd(o);
		Thread t2 = new SynThreadMinus(o);
		// 启动两个线程
		t1.start();
		t2.start();
	}
}
