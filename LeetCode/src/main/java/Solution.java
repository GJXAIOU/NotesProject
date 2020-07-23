import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        HashMap<Integer, Integer> resMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            if (resMap.containsKey(array[i])) {
                Integer oldValue = resMap.get(array[i]);
                resMap.put(array[i], oldValue + 1);
            } else {
                resMap.put(array[i], 1);
            }
        }

        int mid = (array.length - 1) >>> 1;
        Set<Map.Entry<Integer, Integer>> entries = resMap.entrySet();
        for (Map.Entry<Integer, Integer> entry : entries) {
            if (entry.getValue() > mid) {
                return entry.getKey();
            }
        }
        return 0;
    }
}



