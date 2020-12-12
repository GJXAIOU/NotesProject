class Student
{
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public boolean equals(Object obj)
	{
		if( getClass() == obj.getClass())
		{
			if( ((Student)obj).getId() == this.id )
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		return true;
	}
}

public class EqualsDemo {

	public static void main(String[] args) {
		Student s1 = new Student();
		s1.setId("1");
		Student s2 = new Student();
		s2.setId("1");
		
		System.out.println(s1.equals(s2));

	}

}
