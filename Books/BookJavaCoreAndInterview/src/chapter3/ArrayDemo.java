class Person
{
	int id;
}

//��������Ĵ���
public class ArrayDemo 
{
//������
public static void main(String[] args)
{
    //�����������
    Person[] a = null; 
    Person[] b = new Person[3];    
    //ͨ��forѭ����Ϊ���鸳ֵ
	for(int i = 0; i < b.length; i++)
	{
	      //��Person�������������
	      b[i] = new Person();
	}
    //��Person��������Person����
     Person[] c ={  new Person(), new Person(), new Person()   };
    //3�ᱨ�� 
    System.out.println("a.length=" + a.length);
    System.out.println("b.length = " + b.length);
    
    int[] intArr = {1,2,3};
    for(int i = 0; i < intArr.length; i++)
	{
    	System.out.println(intArr[i]);
	}    
  }
}
