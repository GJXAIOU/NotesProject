package chapter4;
import java.io.*;

/**
 * @author GJXAIOU
 * @create 2019-08-18-21:24
 */
public class IORedirectInput {
    public static void main(String[] args) {
        BufferedInputStream bufferedInputStream = null;
        DataInputStream dataInputStream = null;
        String tmp = null;

        try {
            // 将文件中读到的内容放入bufferedInputStream这个内存缓冲区
            bufferedInputStream = new BufferedInputStream(new FileInputStream("c:/in.txt"));
            // 设置重定向
            System.setIn(bufferedInputStream);

            // 从重定向的输入（文件）中读取内容，并逐行输出
            dataInputStream = new DataInputStream(System.in);
            while ((tmp = dataInputStream.readLine())!= null){
                System.out.println(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                bufferedInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
