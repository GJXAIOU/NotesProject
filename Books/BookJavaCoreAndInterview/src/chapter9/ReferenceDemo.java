
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceDemo {
	public static void main(String[] args) {
		// 强引用
		String str=new String("abc");                                     
		// 软引用
		SoftReference<String> softRef=new SoftReference<String>(str);     
		str = null;  // 去掉强引用
		System.gc(); // 垃圾回收器进行回收
		System.out.println(softRef.get());
		
		// 强引用
		String abc = new String("123");
		// 弱引用
		WeakReference<String> weakRef=new WeakReference<String>(abc);     
		abc = null;  // 去掉强引用
		System.gc(); // 垃圾回收器进行回收
		System.out.println(weakRef.get());
	}
}
