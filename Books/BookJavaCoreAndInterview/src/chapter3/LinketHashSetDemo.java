import java.util.HashSet;  
import java.util.Iterator;  
import java.util.LinkedHashSet;  
import java.util.Set;  
public class LinketHashSetDemo {	
	public static void main(String[] args) {		
		Set<String> strHashSet = new HashSet<String>();	
		Set<String> strLinkedHashSet = new LinkedHashSet<String>();		
		for (int i = 0; i < 10; i++) {
            strHashSet.add(String.valueOf(i));  
            strLinkedHashSet.add(String.valueOf(i));  
        }  
          
        Iterator<String> setStringIt = strHashSet.iterator();  
        while(setStringIt.hasNext()) {  
            System.out.print(setStringIt.next() + " ");  
        }  
        System.out.println();  
        Iterator<String> linkedSetStringIt = strLinkedHashSet.iterator();  
        while(linkedSetStringIt.hasNext()) {  
            System.out.print(linkedSetStringIt.next() + " ");  
        }
	}

}
