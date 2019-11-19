
public class IfDemo {

	public static void main(String[] args) {
		int a = 2;
		int b = 3;
		if (a > 0 || ++b < 0) {
			System.out.println(b); // b is 3, not 4
		}
		if (a < 0 && ++b < 0) {
		} else {
			System.out.println(b); // b is 3, not 4
		}
	}

}
