import java.util.ArrayList;
import java.util.List;

public class MemoryLeakDemo {

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
        //往list添加元素
		for (int i = 0; i < 5; i++) {
			String obj = new String("abc");
			list.add(obj);
		}
		//使用list中的元素
		for (int i = 0; i < 5; i++) {
			String obj = list.get(i);
			//使用obj
			obj = null;			
		}
	}
}
