public class ThrowsDemo {
	//�����ú������׳��쳣
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
			e.printStackTrace();//ע��������		
		}
		finally{}
	}
}
