
public class FinalDemo {

	public static void main(String[] args) {
		final String a = "1";
		String b = "1";
        String c = "123";
        String d = b + "23";
        System.out.println(c == d);
        String e = a + "23";
        System.out.println(c == e);
        //a = "4";
	}

}
