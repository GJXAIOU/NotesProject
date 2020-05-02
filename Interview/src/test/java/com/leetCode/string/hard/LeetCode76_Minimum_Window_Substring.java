package string.hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * @author GJXAIOU
 * @create 2019-09-01-22:18
 */
public class LeetCode76_Minimum_Window_Substring {
    public String minWindow(String s, String t) {
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;

        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        // 将字符串转换为字符数组
        for (char c : t.toCharArray()) {
            need.put(c, need.getOrDefault(c, 0)+1);
        }
        // 默认 window 和 need 不匹配
        int match = 0;
        while (right < s.length()){
            char charRight = s.charAt(right);

            if (need.containsKey(charRight)){
                window.put(charRight, window.getOrDefault(charRight, 0) + 1);
                if(need.get(charRight).equals(window.get(charRight))){
                    match++;
                }
            }
            right++;

            while (match == need.size()){
                if(right - left < minLength){
                    // 更新更小子串的位置和长度
                    start = left;
                    minLength = right - left;
                }
                char leftChar = s.charAt(left);
                if (need.containsKey(leftChar)){
                    window.put(leftChar, window.get(leftChar) - 1);
                    if (window.get(leftChar) < need.get(leftChar)){
                        match--;
                    }
                }
                left++;
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : s.substring(start, start + minLength);
    }
}


