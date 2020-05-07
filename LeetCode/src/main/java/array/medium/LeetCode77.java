package array.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/07 13:31
 */
public class LeetCode77 {
    List<List<Integer>> resList = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {

        if (n <= 0 || n < k) {
            return resList;
        }
        LinkedList<Integer> path = new LinkedList<>();
        int[] num = new int[n + 1];
        for (int i = 0; i < num.length; i++) {
            num[i] = i;
        }
        backTrack(num, k, 1, path);
        return resList;
    }

    public void backTrack(int[] num, int k, int start, LinkedList<Integer> path) {
        if (path.size() == k) {
            resList.add(new LinkedList<>(path));
            return;
        }
        for (int i = start; i < num.length; i++) {
            path.add(num[i]);
            backTrack(num, k, i + 1, path);
            path.removeLast();
        }
    }
}
