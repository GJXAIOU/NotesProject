
public class ToStringDemo {
	public static void main(String[] args) {
		int iVal = 10;
		System.out.println(iVal);
		float fVal = 10;
		System.out.println(fVal);		
		char c = 'a';
		System.out.println(c);
		
		Integer i = new Integer(10);
		System.out.println(i);
		System.out.println(i.toString());
		
		StringBuilder builder = new StringBuilder();
		builder.append("123");
		System.out.println(builder);
		System.out.println(builder.toString());
	}
}
