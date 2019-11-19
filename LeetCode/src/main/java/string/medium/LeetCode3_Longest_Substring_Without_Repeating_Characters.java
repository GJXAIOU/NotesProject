package string.medium;

import java.util.HashMap;
import java.util.Map;

/**  使用滑动窗口
 * @author GJXAIOU
 * @create 2019-09-01-19:11
 */
public class LeetCode3_Longest_Substring_Without_Repeating_Characters {
    public int lengthOfLongestSubstring(String s) {
        int[] freq = new int[256];
        // 初始滑动窗口大小为：s[left....right]，初始化为空窗口
        // 到 left == s.length(); right = s.length() - 1 窗口截至；
        int left = 0;
        int right = -1;
        int res = 0;

        // 在每次循环里逐渐改变窗口, 维护freq, 并记录当前窗口中是否找到了一个新的最优值
        while (left < s.length() ){
            if((right + 1) < s.length() && (freq[s.charAt(right + 1)] == 0)){
                right++;
                freq[s.charAt(right)]++;
            }else {
                // right已经到头 || freq[s[r+1]] == 1
                freq[s.charAt(left)]--;
                left++;
            }
            res = res > (right - left + 1) ? res : (right - left + 1);
        }
        return res;
    }


    /**
     *  优化
     */
    public int lengthOfLongestSubstringOpt(String s) {
        int length = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < length; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}






