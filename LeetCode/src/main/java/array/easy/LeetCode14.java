package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/19 20:04
 */
// 代码未完成
public class LeetCode14 {
    public String longestCommonPrefix(String[] strs) {
       if (strs.length == 0 || strs == null){
           return "";
       }
       StringBuffer res = null;
       int j = 0;
        for (int i = 0; i < strs.length - 1; i++) {
            String current = strs[i];
            String next = strs[i + 1];
            while ((j < current.length())&& (current.charAt(j) == next.charAt(j + 1)) ){
                    res.append(strs[i].charAt(j));
                }
            }
        return res.toString();
        }
}
