package math.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/15 10:06
 */
public class LeetCode401 {

    List<String> res;

    public List<String> readBinaryWatch(int num) {
        HashMap<Integer, Integer> time;//初始化时间为0:00
        backtrack(num, 0, time);
        return res;
    }

    HashMap<Integer, Integer> hashmap = {{0, 1}, {1, 2}, {2, 4}, {3, 8}, {4, 1}, {5, 2}, {6, 4},
            {7, 8}, {8, 16}, {9,
            32}};

    void backtrack(int num, int start, HashMap<Integer, Integer> time) {
        if (num == 0) {
            //判断合法性
            if (time.first > 11 || time.second > 59) {
                return;
            }
            String temp_hour = to_string(time.first);
            String temp_minute = to_string(time.second);
            // 如果minute只有一位要补0
            if (temp_minute.size() == 1) {
                temp_minute.insert(0, "0");
            }
            //构造格式
            res.add(temp_hour + ":" + temp_minute);
            return;
        }

        for (int i = start; i < 10; i++) {
            if (time.first > 11 || time.second > 59) {
                continue;
            }
            pair<Integer, Integer> store = time;//保存状态
            if (i >= 4) {
                time.second += hash[i];
            } else {
                time.first += hash[i];
            }
            //进入下一层，注意下一层的start是i+1，即从当前灯的下一盏开始
            backtrack(num - 1, i + 1, time);
            time = store;//恢复状态
        }
    }


}
