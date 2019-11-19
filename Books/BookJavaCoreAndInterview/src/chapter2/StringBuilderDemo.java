

public class StringBuilderDemo {

	public static void main(String[] args) {
		StringBuilder builder = new StringBuilder();
		builder.append("123").append(456); //123456
		System.out.println(builder.substring(0,4));//1234
		System.out.println(builder);//123456
		builder.replace(0,3,"a");
		System.out.println(builder); //a456
		builder.deleteCharAt(0);
		System.out.println(builder); //456
	}

}
