abstract class Employee
{
	protected abstract void talkBusiness();
	
}

class Sales extends Employee
{
	public void talkBusiness()
	{
		System.out.println("Sales talk business."); 
	}
}

class Manager extends Employee
{
	public void talkBusiness()
	{
		System.out.println("Manager talk business."); 
	}
}

public class ExpandDemo {

	public static void main(String[] args) {
		Employee sales = new Sales();
		sales.talkBusiness();
        Employee manager = new Manager();
        manager.talkBusiness();
	}

}
