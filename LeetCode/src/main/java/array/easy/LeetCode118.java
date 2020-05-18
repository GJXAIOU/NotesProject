package array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 21:17
 */
public class LeetCode118 {
    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (numRows <= 0) {
            return res;
        }

        // 首先添加第一行，值为 1
        ArrayList<Integer> rowList = new ArrayList<>();
        rowList.add(1);
        res.add(rowList);

        // 从第 2 行开始就是根据上面一行值计算得到
        for (int curLine = 1; curLine < numRows; curLine++) {
            List<Integer> row = new ArrayList<Integer>();
            // 每行的第一个元素为 1
            row.add(1);
            int i = 1;
            // 每一行元素个数和行号正好是一致的,注意这里 curLine 是从 0 开始的
            while (i < curLine) {
                List<Integer> prevLine = new ArrayList<>();
                // 获取上一行的值
                prevLine = res.get(curLine - 1);
                row.add(prevLine.get(i - 1) + prevLine.get(i));
                i++;
            }
            // 每行最后元素也是 1
            row.add(1);
            res.add(row);
        }
        return res;
    }
}
