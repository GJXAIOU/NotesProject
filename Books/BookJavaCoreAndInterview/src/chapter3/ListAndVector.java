import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class ListAndVector {
	
	
	public static void main(String[] args) {
        Vector v1 = new Vector(100000);
		ArrayList a1 = new ArrayList(100000);
				
		long start=System.currentTimeMillis();  
		for(int i = 0;i<100000;i++)
		{
			v1.add(i);
		}
		long end=System.currentTimeMillis();
		System.out.println("For Vector: " + (end - start));

		start=System.currentTimeMillis();  
		for(int i = 0;i<100000;i++)
		{
			a1.add(i);
		}
		end=System.currentTimeMillis();
		System.out.println("For ArrayList: " + (end - start));
		
	}

}
