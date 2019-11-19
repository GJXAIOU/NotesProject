import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Store {

	private  Lock lock;
    private  Condition notFull;
    private  Condition notEmpty;
	
	private int maxSize;
	private LinkedList<String> storage;

	public Store(int maxSize) {
		lock=new ReentrantLock();
        notFull=lock.newCondition();
        notEmpty=lock.newCondition();
		
		this.maxSize = maxSize;
		storage = new LinkedList<String>();
	}

	// 生产方法
	public void product() {
		lock.lock();
        try {   
        	//如果仓库满了
            while (storage.size() == maxSize ){
                System.out.println(Thread.currentThread().getName()+": wait ");;
              //阻塞生产线程
                notFull.await();
            }
            storage.add("Java Book");
            System.out.println(Thread.currentThread().getName()+": put:"+storage.size());
            Thread.sleep(1000);  
            //唤醒消费线程
            notEmpty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{   
            lock.unlock();
        }
	}

	// 消费方法
	public void consume() {
		lock.lock();
        try {  
        	//如果仓库空了
        	while (storage.size() ==0 ){
                System.out.println(Thread.currentThread().getName()+": wait");;
                notEmpty.await();//阻塞消费线程
            }
        	//取出消费
            System.out.println(storage.poll());
            System.out.println(Thread.currentThread().getName()+": left:"+storage.size());
            Thread.sleep(1000);         
            notFull.signalAll();//唤醒生产线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    } 
}

class ProductThread implements Runnable{
    private Store store;
    public ProductThread(Store store){
    	this.store=store;
    }

    public void run() {
        while(true){
        	store.product();
        }
    }   
}
class ConsumeThread implements Runnable{
	private Store store;
	public ConsumeThread(Store store){
		this.store=store;
    }
    
    public void run() {
        while(true){
            store.consume();
        }
    }   
}

public class ConditionDemo {
	public static void main(String[] arg){
        Store store=new Store(3);
        ProductThread product=new ProductThread(store);
        ConsumeThread consume=new ConsumeThread(store);
        for(int i=0;i<2;i++){
            new Thread(product,"producer-"+i).start();
        }
        for(int i=0;i<2;i++){
            new Thread(consume,"consumer-"+i).start();
        }
    }
}
