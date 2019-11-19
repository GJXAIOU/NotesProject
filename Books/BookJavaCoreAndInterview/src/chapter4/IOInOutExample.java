package chapter4;
import java.io.IOException;
import	java.io.InputStreamReader;
import	java.io.BufferedReader;

/**将键盘输入的内容输出到显示屏
 * @author GJXAIOU
 * @create 2019-08-18-20:46
 */
public class IOInOutExample {
    public static void main(String[] args) {
        // 从system.in即键盘读内容，并放入缓存bufferReader
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String read = null;

        try {
            // 从缓存里面读取数据
            read = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("output : " + read);
    }
}
