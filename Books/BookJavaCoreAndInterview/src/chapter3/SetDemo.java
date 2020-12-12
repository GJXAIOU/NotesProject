import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class SetDemo {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add("1");
		list.add("2");
		list.add("1");
		list.add(null);
		list.add(null);
		
		Set set = new HashSet();
		
		for(int i = 0;i<list.size();i++)
		{
			System.out.println(list.get(i));
			set.add(list.get(i));			
		}
		
		System.out.println(set.size());
		for (Object str : set) {  
		      System.out.println(str);  
		}  
		
	}

}
