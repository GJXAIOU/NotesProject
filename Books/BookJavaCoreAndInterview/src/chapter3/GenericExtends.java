import java.util.ArrayList;
import java.util.List;

class Father
{
}

class Son extends Father
{
}


public class GenericExtends {

	static void copy(List<? super Father> dest, List<? extends Father> src) {  
        for (int i=0; i<src.size(); i++)
        {
            dest.add(src.get(i));
        }
    }  
	
	public static void main(String[] args) {
		List<? extends Father> list1 = new ArrayList<Father>();
		List<? super Father> list2 = new ArrayList<Father>();
		//List<? super Father> list3 = new ArrayList<Son>(); error
		List<? super Son> list4 = new ArrayList<Father>();
		List<? super Son> list5 = new ArrayList<Son>();
		
		List<?> list6 = new ArrayList<String>();
		//List<?> list7 = new ArrayList<?>(); //error
		//List<T> list8 = new ArrayList<String>(); //error
		
		Father f = new Father();
		Son s = new Son();
		
		//list1.add(f);
		list2.add(f);
		list2.add(s);
		list4.add(s);
		//list4.add(f); error
		
		List<Father> srcFatherList = new ArrayList<Father>();
		srcFatherList.add(f);
		
		List<Father> destFatherList = new ArrayList<Father>();
		copy(destFatherList,srcFatherList);
		System.out.println(destFatherList.size());
		
	}
	


}


 