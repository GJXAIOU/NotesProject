package chapter4;
import java.io.*;

/** 实现复制文件的功能:这是使用这个buffer缓冲区一次读写，一般用于小文件
 * @author GJXAIOU
 * @create 2019-08-18-20:18
 */
public class CopyFile {
    public void fileCopy(String src, String dst){
        InputStream inputStream = null;
        OutputStream outputStream = null;

        // 创建输入输出流对象
        try {
            inputStream = new FileInputStream(src);
            outputStream = new FileOutputStream(dst);

            // 获取文件长度，并以此创建缓存
            int fileLength = inputStream.available();
            byte[] buffer = new byte[fileLength];

            // 读取文件，将文件内容放入buffer数组
            inputStream.read(buffer);
            // 将buffer数组中的数据写到目标文件中
            outputStream.write(buffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("文件未找到");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件长度为0，请先写入内容；");
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
