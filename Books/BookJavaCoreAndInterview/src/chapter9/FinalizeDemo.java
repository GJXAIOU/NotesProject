public class FinalizeDemo {
	static FinalizeDemo obj = null;  
    //重写Object里的finalize方法  
    protected void finalize() throws Throwable {  
        System.out.println("In finalize()");  
        obj = this; //给obj加个强引用  
    }  
	
    public static void main(String[] args) throws InterruptedException {  
        obj = new FinalizeDemo();  
        obj = null; //去掉强引用  
        System.gc(); //垃圾回收
        //sleep 1秒，以便垃圾回收线程清理obj对象 
        Thread.sleep(1000);
        if (null != obj) { //在finalize方法复活  
            System.out.println("Still alive.");  
        } else {  
            System.out.println("Not alive.");  
        } 
    }  
}