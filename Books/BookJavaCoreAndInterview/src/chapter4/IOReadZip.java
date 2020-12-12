package chapter4;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * @author GJX
 */
public class IOReadZip {

	public static void main(String[] args) {
		int cont;
		FileInputStream fin = null;
		ZipInputStream zin = null;
		ZipEntry ze = null;
		
		try 
		{
			fin = new FileInputStream("c:\\redirect.zip");
			zin = new ZipInputStream(new BufferedInputStream(fin));
			
			while ((ze = zin.getNextEntry()) != null) 
			{
				System.out.println("file name is:" + ze);
				while ((cont = zin.read()) != -1)
				{
					System.out.write(cont);
				}
				System.out.println();
			}
		} 
		catch (FileNotFoundException e){
			e.printStackTrace();
		} 
		catch (IOException e){
			e.printStackTrace();
		}
		finally
		{
			try {
				fin.close();
				zin.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
