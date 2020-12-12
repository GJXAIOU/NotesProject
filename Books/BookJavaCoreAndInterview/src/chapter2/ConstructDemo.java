class Parent{
	private int value;
	private String msg;
	public Parent()
	{
	    this(0,"call with No Param Function");	
	}
	public Parent(int val)	
	{
		this.value = val;
		System.out.println("with one param");
	}
	public Parent(int val, String msg)
	{
		System.out.println("msg is:" + msg);
		System.out.println("with 2 param");
	}
}

class SubClass extends Parent{
	public SubClass(int i){	
		super(i);
		System.out.println("in subclass");
	}
}

public class ConstructDemo {

	public static void main(String[] args) {
		Parent p0 = new Parent(); 
		Parent p1 = new SubClass(1);
		Parent p2 = new Parent(1,"new Parent");
		
        //SubClass s = new Parent(1); //error
	}

}
