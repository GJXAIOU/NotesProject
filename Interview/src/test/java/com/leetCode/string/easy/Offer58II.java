package string.easy;


/**
 * @Author GJXAIOU
 * @Date 2020/2/24 9:43
 */
public class Offer58II {
    // 方法一：前后直接替换
    public String reverseLeftWords(String s, int n) {
        // 注意：根据题目结果，如果 n 超过 s.length 则返回原字符串，下面这一块是适用于可以超过的情况。
        /**
         if (n == 0 || s.length() == 0) {
         return s;
         }
         int m = 0;
         if (n >= s.length()) {
         m = n % s.length();
         } else {
         m = n;
         }
         */
        if (s.length() > 0 && n > 0 && n < s.length()) {
            String front = s.substring(0, n);
            String after = s.substring(n);
            StringBuilder res = new StringBuilder();
            res.append(after).append(front);
            return res.toString();
        }
        return s;
    }

    // 方法二：模运算
    public String reverseLeftWords2(String s, int n) {
        String res = "";
        if (s.length() > 0 && n > 0 && n < s.length()) {
            for (int i = n; i < n + s.length(); i++) {
                res += s.charAt(i % s.length());
            }
        }
        return res;
    }
}
