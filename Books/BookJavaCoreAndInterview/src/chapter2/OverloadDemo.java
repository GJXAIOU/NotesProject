class PrintMachine
{
	public void print()
	{
		System.out.println("No Pamam");
	}
	
	public void print(int row)
	{
		System.out.println("With 1 int Param");
	}
	
	//error
//	public void print(int colomn)	{
//		System.out.println("With 1 int colomn");
//	}
	
	public void print(int row, int column)
	{
		System.out.println("With 2 int Param");
	}
	
	//error
//	public String print(int row, int column)	{
//		System.out.println("With 2 int Param");
//	}
	
	public void print(int row, String type)
	{
		System.out.println("With 1 int 1 String param");
	}
}


public class OverloadDemo {

	public static void main(String[] args) {
		PrintMachine machine = new PrintMachine();
		machine.print(); // No Pamam
		machine.print(10);//With 1 int Param
		machine.print(30, 20);//With 2 int Param
		machine.print(30, "short");//With 1 int 1 String param
	}

}
