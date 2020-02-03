package string.easy;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/2/3 16:52
 */
public class LeetCode387 {
    public int firstUniqChar(String s) {
        // 处理字符串为空的情况
        if (s.length() == 0) {
            return -1;
        }
        // key:S 中字符，value：该字符的下标
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                map.put(s.charAt(i), i);
            } else {
                map.put(s.charAt(i), Integer.MAX_VALUE);
            }
        }

        // 遍历 map.value，值小于 Integer.MAX_VALUE 则是只出现一次的元素，只要比较他们下标即可
        int resValue = Integer.MAX_VALUE;
        for (Integer value : map.values()) {
            if (value < Integer.MAX_VALUE) {
                resValue = value < resValue ? value : resValue;
            }
        }
        // 处理字符串所有字符都一样的情况
        return resValue == Integer.MAX_VALUE ? -1 : resValue;
    }


    // 方法二：使用数组代替 Map
    public int firstUniqChar2(String s) {

        // 分成两种情况：
        // 第一种为字符串长度小于26的，遍历字符串
        if (s.length() <= 26) {
            // 存储各字符出现次数
            int[] charNum = new int[26];
            char[] chars = s.toCharArray();
            int length = chars.length;
            //第一次遍历,记录各个字符出现次数
            for (int i = 0; i < length; i++) {
                charNum[chars[i] - 'a']++;
            }
            //第二次遍历，按顺序，如果次数为1，返回下标
            for (int i = 0; i < length; i++) {
                if (charNum[chars[i] - 'a'] == 1) {
                    return i;
                }
            }
            //无解
            return -1;
        }

        // 第二种字符串长度大于26，遍历26个字母
        // 反过来，只有26个字符
        int index = -1;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int beginIndex = s.indexOf(ch);
            // 从头开始的位置是否等于结束位置，相等说明只有一个，
            if (beginIndex != -1 && beginIndex == s.lastIndexOf(ch)) {
                //取小的，越小代表越前。
                index = (index == -1 || index > beginIndex) ? beginIndex : index;
            }
        }
        return index;
    }
}
