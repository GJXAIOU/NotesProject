

public class StringtoNum {

	public static void main(String[] args) {
		String num = "123";
		int intVal = Integer.valueOf(num);
		float fltVal = Float.valueOf(num);
		long longVal = Long.valueOf(num);
		System.out.println(intVal);
		System.out.println(fltVal);
		System.out.println(longVal);
	}
}
