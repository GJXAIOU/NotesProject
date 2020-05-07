package math.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GJXAIOU
 * @create 2020/04/15 10:06
 */
public class LeetCode401 {

    List<String> res = new ArrayList<>();
    // LED 灯，前四个为小时，后六个为分钟
    int[] value = new int[]{8, 4, 2, 1, 32, 16, 8, 4, 2, 1};

    public List<String> readBinaryWatch(int num) {
        //判断输入
        if (num < 0) {
            return res;
        }
        backTrack(num, 0, 0, 0);
        return res;
    }

    public void backTrack(int num, int start, int hour, int minute) {
        if (num == 0) {
            // 判断时间是否正确
            if (hour > 11 || minute > 59) {
                return;
            }
            StringBuilder tmp = new StringBuilder();
            //小时
            tmp.append(hour);
            tmp.append(":");
            // 分钟
            if (minute < 10) {
                tmp.append(0);
            }
            tmp.append(minute);
            res.add(new String(tmp));
            return;
        }
        for (int i = start; i < value.length; i++) {
            // 做选择
            if (i < 4) {
                hour += value[i];
            } else {
                minute += value[i];
            }
            // 进入下一层循环，i + 1,即从当前灯的下一盏开始
            backTrack(num - 1, i + 1, hour, minute);
            // 撤销选择
            if (i < 4) {
                hour -= value[i];
            } else {
                minute -= value[i];
            }
        }
    }
}
