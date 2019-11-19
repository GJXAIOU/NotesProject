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
	        //通过allocate()方法来给ByteBuffer分配空间
	        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
	        //通过while循环从src里逐行读
	        while (src.read(buffer)!= -1) {
	            //向dest里写
	            buffer.flip();
	            dest.write(buffer);
	            //清空缓存，以便下次读 
	            buffer.clear();         
	        }
	    }
}
