class SyncReEnter implements Runnable{
    public synchronized void get(){
    	System.out.print(Thread.currentThread().getId() + "\t");
        //在get方法里调用set
        set();
    }
    public synchronized void set(){
        System.out.print(Thread.currentThread().getId() + "\t");
    }
    public void run() {
        get();
    }
}



public class ReEnterSyncDemo {
    public static void main(String[] args) {
    	SyncReEnter demo=new SyncReEnter();
        new Thread(demo).start();
        new Thread(demo).start();
    }
}
