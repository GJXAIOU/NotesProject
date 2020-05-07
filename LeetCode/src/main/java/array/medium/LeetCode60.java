package array.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/06 20:24
 */
public class LeetCode60 {

    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n + 1];
        LinkedList<Integer> path = new LinkedList<>();
        List<Integer> resList = backTrace(n, k, used, path);
        StringBuilder res = new StringBuilder();
        for (Integer integer : resList) {
            res.append(integer);
        }
        return res.toString();
    }


    private volatile int count;

    private LinkedList<Integer> backTrace(int n, int k, boolean[] used,
                                          LinkedList<Integer> path) {
        if (path.size() == n) {
            ++count;
            return path;
        }
        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                path.add(i);
                path = backTrace(n, k, used, path);
                if (count == k) {
                    return path;
                }
                used[i] = false;
                path.removeLast();
            }
        }
        return path;
    }
}
