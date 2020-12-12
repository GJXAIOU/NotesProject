package chapter2;

class Person
{
	void changeCompany(String name)
	{
		name = "IBM";
	}
}

public class FunctionDemo {

	public static void main(String[] args) {
		
		String name = "HP";
		Person person = new Person();
		person.changeCompany(name);
		
		System.out.println(name);		

	}

}
