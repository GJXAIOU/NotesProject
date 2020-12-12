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
//		// 判断是否是学生类型  
//        if (obj instanceof SortedStudent) {  
//        	SortedStudent s = (SortedStudent) obj;  
//            // 如果是学生类型，如果学号相等，则不加入Set  
//            if (s.getId() == this.getId()) {  
//                   return 0;  
//            } else {  
//                 return s.getId()>this.getId()?1:-1;  
//            }  
//              
//        // 不是学生类型对象的话就不要加入它  
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