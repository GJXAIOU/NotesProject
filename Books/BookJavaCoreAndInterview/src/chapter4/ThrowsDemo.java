public class ThrowsDemo {
	//声明该函数会抛出异常
	public static void checkData(int data) throws Exception 
    {
		if(data<0) 
			throw new Exception("Data Error");
	}
	public static void main(String args[]){
		try{
			checkData(-1);
		} 
        catch (Exception e){
			e.printStackTrace();//注意输出结果		
		}
		finally{}
	}
}
