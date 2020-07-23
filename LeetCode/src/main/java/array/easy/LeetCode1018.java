package array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/22 14:52
 */
public class LeetCode1018 {
    public List<Boolean> prefixesDivBy5(int[] A) {
        ArrayList<Boolean> resList = new ArrayList<>();
        int cur = 0;
        for (int i = 0; i < A.length; i++) {
            // 位运算优先级较低，必须加括号
            cur = (cur << 1) + A[i];
            cur = cur % 5;
            if (cur == 0) {
                resList.add(true);
            } else {
                resList.add(false);
            }
        }
        return resList;
    }
}
