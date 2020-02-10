package array.medium;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/10 19:52
 */
public class LeetCode454 {

    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int res = 0;
        // 首先计算 C 和 D 中所有元素的和，放入哈希表中
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int sumCD = C[i] + D[j];
                if (map.containsKey(sumCD)) {
                    map.put(sumCD, map.get(sumCD) + 1);
                } else {
                    map.put(sumCD, 1);
                }

            }
        }

        // 遍历 A 和 B 的所有可能性，同时查询哈希表
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int aim = 0 - (A[i] + B[j]);
                if (map.containsKey(aim)) {
                    res += map.get(aim);
                }
            }
        }
        return res;
    }
}
