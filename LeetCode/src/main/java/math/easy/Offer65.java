package math.easy;

/**
 * @author GJXAIOU
 * @create 2020/04/12 21:02
 */
public class Offer65 {
    public int add(int a, int b) {
        while (b != 0) {
            int temp = a ^ b;
            b = (a & b) << 1;
            a = temp;
        }
        return a;
    }
}
