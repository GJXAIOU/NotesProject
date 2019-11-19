class T extends Thread {
	//得到另外一个线程对象
	T t;
	public void run() {
		sync();
		//线程结束时调用
		System.out.println("Thread finished");
	}
	//sync为同步方法，只有获得当前对象锁之后才能使用该方法
	public synchronized void sync(){
		try{
			sleep(2000);
		} 
        catch (InterruptedException e){
			e.printStackTrace();
		}
		//调用另外一个对象t的同步方法f()
		t.anoSync();
	}
	//anoSync也是同步方法
	public synchronized void anoSync() {	}
}
public class DeadLock extends Thread 
{
	public static void main(String arg[]) 
{
		//创建两个线程
		T t1 = new T();
		T t2 = new T();
		t1.t = t2;
		t2.t = t1;
		//启动两个线程
		t1.start();
		t2.start();
		System.out.println("main finished");
	}
}
