class Base
{ 
	public void print(){}
	//是重载，因为参数不同  
	public void print(int row){}
	//是重载，因为参数类型不同
	public void print(String type){}	
}

class Child extends Base
{
	//是覆盖，覆盖了父类的同名无参方法
	public void print(){}
	//出错，因为父类已经有个带int参的同名方法
	//public String print(int row){}
	//是覆盖，覆盖了父类的带String参的同名方法
	public void print(String type){}
	//是重载，因为参数个数不同
	public void print(int row, String type){}	
}

public class DifferenceDemo {
	public static void main(String[] args) {	}
}
