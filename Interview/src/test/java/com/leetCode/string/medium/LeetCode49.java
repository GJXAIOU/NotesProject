package string.medium;

import java.util.*;

/**
 * @Author GJXAIOU
 * @Date 2020/2/9 10:14
 */
public class LeetCode49 {
    // 方法一：哈希表
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0 || strs == null) {
            return new ArrayList<>();
        }

        HashMap<String, List> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            // 首先将每个元素（字符串）排序；
            char[] charArray = strs[i].toCharArray();
            Arrays.sort(charArray);
            // 排序之后每个字符串为 key
            String key = String.valueOf(charArray);
            // 然后将其加入 HashMap,如果没有改 key 则新建 List
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            // 获取该 key（排序后的字符串） 后面的链表加上原始字符串数据
            map.get(key).add(strs[i]);
        }
        return new ArrayList(map.values());
    }

    // 方法二：字符计数

    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList();
        }
        Map<String, List> ans = new HashMap<String, List>();
        int[] count = new int[26];

        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        return new ArrayList(ans.values());
    }

}
