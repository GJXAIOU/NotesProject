
public class BonusLevel {

	public static void main(String[] args) {
		char gradeLevel = 'C'; 
		switch (gradeLevel) 
{
		case 'A':
         {
			System.out.println(gradeLevel + ",bonus is 1000.");
			break;
         }
		case 'B':
         {
        	 System.out.println(gradeLevel + ",bonus is 800.");
			break;
         }
		case 'C':
         {
        	 System.out.println(gradeLevel + ",bonus is 400.");
			break;
         }
		case 'D':
         {
        	 System.out.println(gradeLevel + ",bonus is 200.");
			break;
         }
		default:
         {
        	 System.out.println( "No bonus.");
         }
		}
	}

}
