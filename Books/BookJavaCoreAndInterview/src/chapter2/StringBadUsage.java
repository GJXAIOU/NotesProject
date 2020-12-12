
public class StringBadUsage {

	public static void main(String[] args) {
		String a = "123456789";
		System.out.println(a.substring(0,5));//12345
		
		String b = "123456789";
		b.substring(0,5);
		//b = b.substring(0,5);
        System.out.println(b);//123456789
        
        String c = "123456789";
        c.replace('1', '2');
		//c = c.replace('1', '2');
        System.out.println(c);//123456789
        
        String num = "1";
        for(int i = 0;i<100;i++)
        {
        	num = num + "0";
        }
	}

}
