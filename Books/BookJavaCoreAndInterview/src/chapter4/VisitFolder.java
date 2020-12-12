import java.io.File;


public class VisitFolder {
	 static void getCSVInFolder(String filePath) {
		  File folderName = new File(filePath);
		  File flist[] = folderName.listFiles();
		  if (flist == null || flist.length == 0) {
		      return;
		  }
		  String fileName = null;
		  
		  for (File f : flist) {
		      if (f.isDirectory()) {
		          //������ļ��У���ݹ����
		          getCSVInFolder(f.getAbsolutePath());
		      } else {
		          //������ļ������ж��Ƿ���csv�ļ� 
		    	  //fileName.substring(fileName.lastIndexOf(".") + 1)
		    	  fileName = f.getName();
		    	  if(fileName.substring(fileName.lastIndexOf(".") + 1).equals("csv"))
		    	  {  
		    	      System.out.println(f.getAbsolutePath());
		    	  }		    	  
		      }
		  }
	 }
	 
	 public static void main(String[] args)
	 {
		 getCSVInFolder("c://1");
	 }
	
}
