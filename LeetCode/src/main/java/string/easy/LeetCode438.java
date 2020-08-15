package string.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author GJXAIOU
 * @Date 2020/8/1 21:51
 */
public class LeetCode438 {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        int[] pLetter = new int[26];
        // 记录 p 里面的字母分别有几个
        for (int i = 0; i < p.length(); i++) {
            pLetter[p.charAt(i) - 'a']++;
        }

        int start = 0;
        int end = 0;
        //记录两个指针之间的数字都有几个
        int[] betweenLetter = new int[26];
        while (end < s.length()) {
            //每一次拿到end指针对应的字母
            int c = s.charAt(end) - 'a';
            end++;
            //让这个字母的数量+1
            betweenLetter[c]++;

            //如果这个字母的数量比p里面多了,说明这个start坐标需要排除
            while (betweenLetter[c] > pLetter[c]) {
                betweenLetter[s.charAt(start++) - 'a']--;
            }
            if (end - start == p.length()) {
                result.add(start);
            }
        }
        return result;
    }
}
