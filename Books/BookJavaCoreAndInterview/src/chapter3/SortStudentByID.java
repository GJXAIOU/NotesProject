import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


class SortedStudent //implements Comparable
{
	private int id;
	public SortedStudent(int id)
	{
		this.id = id;
	}
	
	public int getId()
	{
		return id;
	}
	
	public boolean equals(SortedStudent stu)
	{
			if( stu.getId() == this.id )
			{
				return true;
			}
			else
			{
				return false;
			}	
	}

//	public int compareTo(Object obj) {
//		// �ж��Ƿ���ѧ������  
//        if (obj instanceof SortedStudent) {  
//        	SortedStudent s = (SortedStudent) obj;  
//            // �����ѧ�����ͣ����ѧ����ȣ��򲻼���Set  
//            if (s.getId() == this.getId()) {  
//                   return 0;  
//            } else {  
//                 return s.getId()>this.getId()?1:-1;  
//            }  
//              
//        // ����ѧ�����Ͷ���Ļ��Ͳ�Ҫ������  
//        } else {  
//            return 0; 
//        }  
//          
//	}
}


public class SortStudentByID {

	public static void main(String[] args) {
		
		
		SortedStudent s1 = new SortedStudent(1);
		SortedStudent s2 = new SortedStudent(2);
		SortedStudent s3 = new SortedStudent(3);
		SortedStudent s4 = new SortedStudent(4);
		Set<SortedStudent> stuSet = new TreeSet<SortedStudent>();
		stuSet.add(s2);
		stuSet.add(s4);
		stuSet.add(s1);
		stuSet.add(s3);
		Iterator<SortedStudent> itStu = stuSet.iterator();
		
		while(itStu.hasNext())
		{
			System.out.println(itStu.next().getId());
		}
		
	}
}