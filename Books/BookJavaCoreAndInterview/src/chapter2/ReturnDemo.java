
class Emp
{
	int addSalary(int oldNum)
	{
		return oldNum + 1000;
	}
}

public class ReturnDemo {
    public static void main(String[] args) {		
		Emp emp = new Emp();
		int currentSalary = emp.addSalary(5000);		
		System.out.println(currentSalary);		
	}
}
