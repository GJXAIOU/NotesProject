import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/4/7 9:56
 */
public class Demo {
    public static void main(String[] args) {
        boolean powerOfTwo = isPowerOfTwo(32769);
        System.out.println(powerOfTwo);
    }

    public static boolean isPowerOfTwo(int n){
        if (n < 2){
            return false;
        }
        int max = (int)Math.pow(2, (int)(Math.log10(0x7fffffff) / Math.log10(2)));
        if (max % n == 0){
            return true;
        }
        return false;
    }
}
