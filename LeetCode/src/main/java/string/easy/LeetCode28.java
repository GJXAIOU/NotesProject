package string.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/7 16:19
 */
public class LeetCode28 {
    public int strStr(String haystack, String needle) {
        // 特殊值判断
        if (haystack == null || needle == null || needle.length() < 1) {
            return 0;
        }
        if (haystack.length() < needle.length()) {
            return -1;
        }

        char[] hayArray = haystack.toCharArray();
        char[] needArray = needle.toCharArray();
        int hayIndex = 0;
        int needIndex = 0;
        int[] next = getNextArray(needArray);
        while (hayIndex < hayArray.length && needIndex < needArray.length) {
            if (hayArray[hayIndex] == needArray[needIndex]) {
                hayIndex++;
                needIndex++;
                // 如果 next 数组中值等于 -1 ，说明是第一个元素，说明当前 str1  中值连 str2 第一个字母都匹配不上，则直接从 str1 的下一个开始进行匹配
            } else if (next[needIndex] == -1) {
                hayIndex++;
            } else {
                // 滑动大小为 needArray 当前字符 needIndex 的最大前后缀匹配长度
                needIndex = next[needIndex];
            }
        }
        return needIndex == needArray.length ? hayIndex - needIndex : -1;
    }

    // 求解 next 数组方法
    public static int[] getNextArray(char[] ms) {
        if (ms.length == 1) {
            return new int[]{-1};
        }
        int[] next = new int[ms.length];
        next[0] = -1;
        next[1] = 0;
        // 需要求值的位置
        int pos = 2;
        int cn = 0;
        while (pos < next.length) {
            if (ms[pos - 1] == ms[cn]) {
                next[pos++] = ++cn;
            } else if (cn > 0) {
                cn = next[cn];
            } else {
                next[pos++] = 0;
            }
        }
        return next;
    }
}
