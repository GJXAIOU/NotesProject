package string.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/4 13:23
 */
public class LeetCode38 {
    public String countAndSay(int n) {

        String res = "1";
        char pre = res.charAt(0);
        for (int i = 2; i <= n; i++) {
            StringBuilder temp = new StringBuilder();
            int count = 1;
            for (int j = 1; j < res.length(); j++) {
                // 当前位和前一位元素值进行比较，如果相同则 count++，不相同则将前一个元素个数和值追加，然后 pre 指向当前元素
                if (pre == res.charAt(j)) {
                    count++;
                } else {
                    temp.append(count).append(pre);
                    pre = res.charAt(j);
                    count = 1;
                }
            }
            // 每次换行之后更改 temp 值
            temp.append(count).append(pre);
            res = temp.toString();
        }
        return res;
    }
}
