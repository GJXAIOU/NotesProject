package chapter4;
import java.io.*;

/** 输出重定向：将输出至文件
 * @author GJXAIOU
 * @create 2019-08-18-20:53
 */
public class IORedirectOutput {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        PrintStream ps = null;
        // fos对象中包含了往文件写的内容
        try {
            // fos对象包含了往文件写的内容
            fos = new FileOutputStream("c:/out.txt");
            // bos对象包含了缓存向外写的内容
            bos = new BufferedOutputStream(fos);
            ps = new PrintStream(bos);

            // 设置重定向
            System.setOut(ps);

            // 这里向文件输出
            System.out.println("redirect to out.txt");

            // 强制将缓存中内容输出
            ps.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            ps.close();
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
