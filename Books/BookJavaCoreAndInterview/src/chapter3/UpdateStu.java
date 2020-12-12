import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class UpdateStu {

	public static void main(String[] args) {
		 HashMap<String,String> dbHM = new HashMap<String,String>();
		 HashMap<String,String> fileHM = new HashMap<String,String>();
		 
		 dbHM.put("1", "A1");
		 dbHM.put("2", "A1");
		 dbHM.put("3", "A1");
		 
		 fileHM.put("2", "A2");
		 fileHM.put("3", "A1");
		 fileHM.put("4", "A2");
		 
		 String idInDB = null;
		 String classNameInDB = null;
		 String idInFile = null;
		 String classNameInFile = null;
		 
		 Iterator<Entry<String, String>> dbIt = dbHM.entrySet().iterator();
		 
		 while (dbIt.hasNext()) {
			Map.Entry<String,String> entry = (Map.Entry<String,String>)dbIt.next();
			idInDB=entry.getKey();
			
			classNameInDB = entry.getValue();
			
			if(!fileHM.containsKey(idInDB)){
				//delete this student
				System.out.println("Need Delete ID:" + idInDB);
			}
			else
			{
				classNameInFile = fileHM.get(idInDB);
				if(!classNameInFile.equals(classNameInDB))
				{
					//update this student
					System.out.println("Need Update ID:" + idInDB);
				}
			}
		 }
		 
		 Iterator<Entry<String, String>> fileIt = fileHM.entrySet().iterator();
		 
		 while (fileIt.hasNext()) {
				Map.Entry<String,String> entry = (Map.Entry<String,String>)fileIt.next();
				idInFile=entry.getKey();				
				classNameInFile = entry.getValue();
				
				if(!dbHM.containsKey(idInFile)){
					//insert this student
					System.out.println("Insert ID:" + idInFile + " Class Name is: " + classNameInFile);
				}				
			 }
		 
	}

}
