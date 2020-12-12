import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class TreeSetDemo {

	public static void main(String[] args) {
		Set<String> treeSet = new TreeSet<String>();
		
		treeSet.add("4");
		treeSet.add("3");
		treeSet.add("1");
		treeSet.add("2");
		//treeSet.add(null); //会有运行期异常
		
		Iterator<String> setStringIt = treeSet.iterator();  
	    while(setStringIt.hasNext()) {  
	          System.out.print(setStringIt.next() + " ");  
	    }  

	}

}
