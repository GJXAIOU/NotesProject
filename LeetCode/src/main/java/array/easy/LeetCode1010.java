package array.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2020/05/22 14:40
 */
public class LeetCode1010 {
    public int numPairsDivisibleBy60(int[] time) {
        // 预处理：把数组中的元素全都模 60
        int len = time.length;
        for (int i = 0; i < len; i++) {
            time[i] = time[i] % 60;
        }
        // 注意：[60, 60, 60] 会被处理成 [0, 0, 0]
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0; i < len; i++) {
            // 注意：要记得模 60
            int residue = (60 - time[i]) % 60;

            Integer preCount = map.get(residue);
            if (preCount != null) {
                res += preCount;
            }

            // 计数，应该放在统计之后做，因为后面的参考前面的
            Integer curCount = map.get(time[i]);
            if (curCount == null) {
                map.put(time[i], 1);
            } else {
                map.put(time[i], curCount + 1);
            }
        }
        return res;
    }
}
