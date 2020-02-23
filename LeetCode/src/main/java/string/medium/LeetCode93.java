package string.medium;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/2/23 10:17
 */
public class LeetCode93 {

    // 分析:从 0 位置开始，后面分为 3 段，从 1 位置（前面 0 位置已经分割好）开始后面分为 2 段
    public List<String> restoreIpAddresses(String s) {
        List<String> resList = new ArrayList<String>();
        // 合法性判断：首先因为每位 IP 地址长度为 1 - 3 位，所以 s 长度 < 4 或者 > 12 则划分结果都是错误的
        if (s.length() < 4 || s.length() > 12) {
            return resList;
        }
        ArrayDeque<String> path = new ArrayDeque<>(4);
        splitIP(s, s.length(), 4, 0, path, resList);
        return resList;
    }

    public void splitIP(String s, int sLength, int residueSplitTimes, int beginIndex,
                        Deque<String> path, List<String> resList) {
        // 如果划分到结尾了，并且划分了 4 段，则返回
        if (beginIndex == sLength) {
            if (residueSplitTimes == 0) {
                resList.add(String.join(".", path));
            }
            return;
        }

        // 因为每一次划分都有三种可能性，一位，两位、三位
        for (int i = beginIndex; i < beginIndex + 3; i++) {
            if (i >= sLength) {
                break;
            }
            if (residueSplitTimes * 3 < sLength - i) {
                continue;
            }

            // 看上面三种划分是否结果能够构成 IP， 如果可以就是一种可能性，可以继续划分
            if (judgeIfIpSegment(s, beginIndex, i)) {
                String curIPSegment = s.substring(beginIndex, i + 1);
                path.addLast(curIPSegment);
                splitIP(s, sLength, residueSplitTimes - 1, i + 1, path, resList);
                path.removeLast();
            }
        }
    }

    public boolean judgeIfIpSegment(String s, int beginIndex, int endIndex) {
        // 当待判定区间长度大于 1 时候不能以 0 开头
        if (endIndex - beginIndex + 1 > 1 && s.charAt(beginIndex) == '0') {
            return false;
        }

        // 转为整数 int
        int res = 0;
        while (beginIndex <= endIndex) {
            res = res * 10 + s.charAt(beginIndex) - '0';
            beginIndex++;
        }

        return res >= 0 && res <= 255;

    }
}

