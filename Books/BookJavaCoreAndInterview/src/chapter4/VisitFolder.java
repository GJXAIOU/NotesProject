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
		          //如果是文件夹，则递归调用
		          getCSVInFolder(f.getAbsolutePath());
		      } else {
		          //如果是文件，则判断是否是csv文件 
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
