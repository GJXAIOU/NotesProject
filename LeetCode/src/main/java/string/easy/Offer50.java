package string.easy;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author GJXAIOU
 * @create 2020/04/12 21:48
 */
public class Offer50 {
    // 方法一：HashMap
    public char firstUniqChar(String s) {
        // 处理字符串为空的情况
        if (s.length() == 0) {
            return ' ';
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
        if (resValue == Integer.MAX_VALUE) {
            return ' ';
        } else {
            return s.charAt(resValue);
        }

    }

    // 方法二：Hash 表中存放的是 boolean 值，然后遍历字符串（就可以保证从头开始）
    public char firstUniqChar2(String s) {
        HashMap<Character, Boolean> resMap = new HashMap<>();
        char[] sc = s.toCharArray();
        for (char c : sc) {
            resMap.put(c, !resMap.containsKey(c));
        }
        for (char c : sc) {
            if (resMap.get(c)) {
                return c;
            }
        }
        return ' ';
    }
}
