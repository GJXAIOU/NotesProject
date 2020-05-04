package array.medium;

import java.util.*;

/**
 * @author GJXAIOU
 * @create 2020/05/04 17:06
 */
public class LeetCode406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new MyCompare());
        ArrayList<int[]> res = new ArrayList<>();
        for (int i = 0; i < people.length; i++) {
            res.add(people[i][1], people[i]);
        }
        return res.toArray(new int[people.length][2]);
    }

    class MyCompare implements Comparator<int[]> {
        @Override
        public int compare(int[] o1, int[] o2) {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        }
    }
}

