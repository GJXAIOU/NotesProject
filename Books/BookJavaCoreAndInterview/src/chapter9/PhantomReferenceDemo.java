

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomReferenceDemo {

	public static void main(String[] args) {
		
		String obj = new String("123");  
	    ReferenceQueue<String> refQueue = new ReferenceQueue<String>();  
	    PhantomReference<String> phantom = new PhantomReference<String>(obj,  
	            refQueue);
	    System.out.println(phantom);
	    // 调用phanRef.get()不管在什么情况下会一直返回null
	    System.out.println(phantom.get());
	}
}
