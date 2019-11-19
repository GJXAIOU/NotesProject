import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class NIOMappedByteBufferDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		int length = 1024*1024 * 10;//10M		
		MappedByteBuffer out = 
	                new RandomAccessFile("c:\\bigFile.txt", "rw").getChannel()
	                .map(FileChannel.MapMode.READ_WRITE, 0, length);
	    for (int i = 0; i < length; i++) {
	        out.put((byte) 'a');
	    }
	    
	    //只读其中的前10位
	    for (int i = 0; i < 10; i++) {
            System.out.print((char) out.get(i));
        }
        
	}
}

	



