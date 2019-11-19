

public class LoopDemo {

	public static void main(String[] args) {
		int sum=0;		
		for(int i=1;i<100;i++)
		//for(int i=1;i<101;i++)
		{
			sum = sum+i;
		}
		System.out.println(sum);//结果是4950

		int startVal = 1;
		//int startVal = 0;
		sum=0;
		while(startVal++<100)
		{
			sum = sum+startVal;
		}
		System.out.println(sum);//结果是5049
		
		int visitTime = 0;
		int bonus=0;
		do
		{
			bonus++;
		}
		while(visitTime++<10);
		System.out.println(bonus);//结果是11
	}

}
