package math.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/25 13:43
 */
public class Offer15 {

    public int hammingWeight(int n) {
        int m = 1;
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((m & n) != 0){
                count++;
            }
            m <<= 1;
        }
        return count;
    }

}
