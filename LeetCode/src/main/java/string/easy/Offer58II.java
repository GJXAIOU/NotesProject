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

    // 方法三：分别旋转
    public String reverseLeftWords3(String str, int n) {
        if (str == null || str.length() == 0 || n % str.length() == 0) {
            return str;
        }
        n %= str.length();
        char[] inputString = str.toCharArray();
        reverse(inputString, 0, inputString.length - 1);
        reverse(inputString, 0, inputString.length - n - 1);
        reverse(inputString, inputString.length - n, inputString.length - 1);
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < inputString.length; i++) {
            res.append(inputString[i]);
        }
        return res.toString();
    }

    public void reverse(char[] inputString, int begin, int end) {
        while (begin < end) {
            char temp = inputString[begin];
            inputString[begin] = inputString[end];
            inputString[end] = temp;
            begin++;
            end--;
        }
    }

}
