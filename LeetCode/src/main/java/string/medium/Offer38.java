package string.medium;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/05/06 14:14
 */
public class Offer38 {
    List<String> res = new ArrayList<>();

    public String[] permutation(String s) {
        if (s.length() == 0) {
            return res.toArray(new String[res.size()]);
        }
        char[] sArray = s.toCharArray();
        Arrays.sort(sArray);
        StringBuilder path = new StringBuilder();
        boolean[] used = new boolean[sArray.length];
        backtrack(sArray, path, used);
        return res.toArray(new String[res.size()]);
    }


    public void backtrack(char[] sArray, StringBuilder path, boolean[] used) {
        // 结束条件
        if (path.length() == sArray.length) {
            res.add(path.toString());
            return;
        }
        // 选择列表
        for (int i = 0; i < sArray.length; i++) {
            //从给定的数中除去，用过的数，就是当前的选择列表
            if (!used[i]) {
                // 剪枝
                if ((i >= 1) && (sArray[i - 1] == sArray[i]) && !used[i - 1]) {
                    continue;
                }// 做出选择
                path.append(sArray[i]);
                used[i] = true;
                // 进入下一层
                backtrack(sArray, path, used);
                // 撤销选择
                path.deleteCharAt(path.length() - 1);
                used[i] = false;
            }
        }
    }
}
