

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Field;

public class PhantomReferenceCleaner {

	public static void main(String[] args) {
		
		String abc = new String("123");
		ReferenceQueue<String> referenceQueue = new ReferenceQueue<String>();
        PhantomReference<String> ref = new PhantomReference<String>(abc,
                referenceQueue);
        abc = null;
        System.gc();
        //最好用Debug来调试
        Object obj = referenceQueue.poll();
        if (obj != null) {          
            Field rereferentVal = null;
			try {
				rereferentVal = Reference.class
				          .getDeclaredField("referent");
				rereferentVal.setAccessible(true);
	            System.out.println("Before GC Clear：" + rereferentVal.get(obj).toString());
			} catch (Exception  e) {
				e.printStackTrace();
			}
        }
	}
}
