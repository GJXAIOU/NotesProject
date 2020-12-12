public class BaseExceptionDemo {
	public static void main(String[] args) {
		int i = 0;
		int a[] = {1,2,3,4};
		try{
		  System.out.println("Before while.");
		  //故意引发数组越界异常 
		  while (i < 5)          {
			  System.out.println(a[i]);
			  i++;
		  }
		  System.out.println("After while.");
	   }
       catch(Exception e){
			System.out.println("Execption happens!");
			//return;
			//System.exit(0);
	   }
	   finally{
		   a = null;
	   }
	   System.out.println("After try-catch");
	}
}