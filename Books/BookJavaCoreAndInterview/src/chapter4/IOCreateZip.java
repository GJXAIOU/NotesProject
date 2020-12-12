package chapter4;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IOCreateZip {

	public static void main(String[] args) {
		// 设置压缩后和带压缩的文件名
		String zipFile = "c:\\redirect.zip";
		String file1 = "c:\\redirect.txt";
		int zipRes = -1;
		
		 FileOutputStream fout = null;
		 ZipOutputStream zout = null;
		 BufferedOutputStream bout = null;			
		 FileInputStream fisOne = null;
		 BufferedInputStream bisOne = null;
		
		try 
		{
			// 定义 IO 对象
			// 用 fout 的 FileOutputStream 类型的对象指向了待生成的 zip 文件，把fout的内容输出到zip文件中
            fout = new FileOutputStream(zipFile);
			zout = new ZipOutputStream(fout);
			bout = new BufferedOutputStream(zout);			
			fisOne = new FileInputStream(file1);
			bisOne = new BufferedInputStream(fisOne);
			zout.putNextEntry(new ZipEntry("redirect.txt"));
			// 逐行读文件，把读到的内容添加到压缩流中
			while ((zipRes = bisOne.read()) != -1) {
				bout.write(zipRes);
			}
			// 强制输出
			bout.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				bisOne.close();
				fout.close();
	            zout.close();			
	            fisOne.close();
	            bout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
