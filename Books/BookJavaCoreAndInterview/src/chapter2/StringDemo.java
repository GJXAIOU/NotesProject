
public class StringDemo {

	public static void main(String[] args) {
		String a = "abc";
		String b = "abc";
		System.out.println(a == b);
		
		
		Integer i1 = 10;
		Integer i2 = 10;
		System.out.println(i1 == i2);
		
		String b1 =new String("abc");
		System.out.println(a == b1);
		String a1 =new String("abc");
		System.out.println(a1 == b1);
		
		String c = "a";
		String d = c + "bc";
		String e = "a" + "bc";
		System.out.println(a == d);
		System.out.println(a == e);		
	}
}
