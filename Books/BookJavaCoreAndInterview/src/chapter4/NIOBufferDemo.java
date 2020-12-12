import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOBufferDemo {	 
	    public static void main(String[] args) throws IOException {
	    	int bufferSize = 1024;	    	
	        FileChannel src = new FileInputStream("c:\\source.txt").getChannel();
	        FileChannel dest = new FileOutputStream("c:\\dest.txt").getChannel();
	        //ͨ��allocate()��������ByteBuffer����ռ�
	        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
	        //ͨ��whileѭ����src�����ж�
	        while (src.read(buffer)!= -1) {
	            //��dest��д
	            buffer.flip();
	            dest.write(buffer);
	            //��ջ��棬�Ա��´ζ� 
	            buffer.clear();         
	        }
	    }
}
