class Base
{ 
	public void print(){}
	//�����أ���Ϊ������ͬ  
	public void print(int row){}
	//�����أ���Ϊ�������Ͳ�ͬ
	public void print(String type){}	
}

class Child extends Base
{
	//�Ǹ��ǣ������˸����ͬ���޲η���
	public void print(){}
	//������Ϊ�����Ѿ��и���int�ε�ͬ������
	//public String print(int row){}
	//�Ǹ��ǣ������˸���Ĵ�String�ε�ͬ������
	public void print(String type){}
	//�����أ���Ϊ����������ͬ
	public void print(int row, String type){}	
}

public class DifferenceDemo {
	public static void main(String[] args) {	}
}
