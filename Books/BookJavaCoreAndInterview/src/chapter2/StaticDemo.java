public class StaticDemo {

	//private int value = -2;
	private static int value = -2;
	
	static void print()
	//void print()
	{
		System.out.println(Math.abs(value));
	}
	
	public static void main(String[] args) {
		StaticDemo.print();

	}

}
