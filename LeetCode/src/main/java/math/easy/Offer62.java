package math.easy;

import java.util.LinkedList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/12 15:17
 */
public class Offer62 {
    public int lastRemaining(int n, int m) {
        if (n == 0) {
            return 0;
        }
        List<Integer> inputValueList = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            inputValueList.add(i);
        }

        int temp = n;
        temp = getLive(temp, m);
        return temp - 1;
    }

    public static int getLive(int i, int num) {
        if (i == 1) {
            return 1;
        }
        // 计算出新编号对应的旧编号，将该旧编号作为下一次计算的新编号
        return (getLive(i - 1, num) + num - 1) % i + 1;
    }
}
