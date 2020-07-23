package array.easy;

import java.util.*;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:22
 */
public class LeetCode914 {
    public static boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length == 0) {
            return false;
        }
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i : deck) {
            hashMap.put(i, hashMap.getOrDefault(i, 0) + 1);
        }
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            list.add(entry.getValue());
        }

        Collections.sort(list);
        // min 就是所有元素出现次数最小的
        int min = list.get(0);
        boolean isX = false;
        for (int i = 2; i <= min; i++) {
            for (Integer j : list) {
                if (j % i != 0) {
                    isX = false;
                    break;
                }
                isX = true;
            }
            if (isX) {
                return true;
            }
        }
        return false;
    }
}
