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
		// ����ѹ����ʹ�ѹ�����ļ���
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
			// ���� IO ����
			// �� fout �� FileOutputStream ���͵Ķ���ָ���˴����ɵ� zip �ļ�����fout�����������zip�ļ���
            fout = new FileOutputStream(zipFile);
			zout = new ZipOutputStream(fout);
			bout = new BufferedOutputStream(zout);			
			fisOne = new FileInputStream(file1);
			bisOne = new BufferedInputStream(fisOne);
			zout.putNextEntry(new ZipEntry("redirect.txt"));
			// ���ж��ļ����Ѷ�����������ӵ�ѹ������
			while ((zipRes = bisOne.read()) != -1) {
				bout.write(zipRes);
			}
			// ǿ�����
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
