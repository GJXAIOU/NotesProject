class Person
{
	int id;
}

//关于数组的代码
public class ArrayDemo 
{
//主函数
public static void main(String[] args)
{
    //定义各种数组
    Person[] a = null; 
    Person[] b = new Person[3];    
    //通过for循环，为数组赋值
	for(int i = 0; i < b.length; i++)
	{
	      //将Person对象放入数组中
	      b[i] = new Person();
	}
    //在Person数组里存放Person对象
     Person[] c ={  new Person(), new Person(), new Person()   };
    //3会报错 
    System.out.println("a.length=" + a.length);
    System.out.println("b.length = " + b.length);
    
    int[] intArr = {1,2,3};
    for(int i = 0; i < intArr.length; i++)
	{
    	System.out.println(intArr[i]);
	}    
  }
}
