package array.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/1/19 20:04
 */
public class LeetCode14 {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // 查找后面值以 prefix 开头
            while (strs[i].indexOf(prefix) != 0) {
                // 如果不是 prefix 就删除最后元素
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }
}
