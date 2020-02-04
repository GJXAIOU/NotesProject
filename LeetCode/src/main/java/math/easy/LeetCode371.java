package math.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/4 16:01
 */
public class LeetCode371 {
    public int getSum(int a, int b) {
        while(b != 0){
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }

}
