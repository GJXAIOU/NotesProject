package array.easy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:42
 */
public class LeetCode989 {
    public List<Integer> addToArrayForm(int[] A, int K) {
        int cur = K;
        List<Integer> ans = new ArrayList();

        int index = A.length - 1;
        while (index >= 0 || cur > 0) {
            if (index >= 0) {
                cur += A[index];
            }
            ans.add(cur % 10);
            cur /= 10;
            index--;
        }

        Collections.reverse(ans);
        return ans;
    }
}
