package string.easy;

/**
 * @author GJXAIOU
 * @create 2020/04/13 20:03
 */
public class Offer58I {

    // 方法一：使用内置函数
    public String reverseWords(String s) {
        String[] sArray = s.trim().split(" ");
        StringBuilder res = new StringBuilder();
        for (int i = sArray.length - 1; i >= 1; i--) {
            // 可能有多个空格
            if (sArray[i].equals(" ")) {
                continue;
            }
            res.append(sArray[i]).append(" ");
        }
        // 最后一个不添加空格
        res.append(sArray[0]);
        // 去除最后的末尾空格
        return res.toString().trim();
    }

    // 方法二：双指针
    public String reverseWords2(String s) {
        // 首先去除前后空格
        s = s.trim();
        int right = s.length() - 1;
        int left = right;
        StringBuilder res = new StringBuilder();
        while (left >= 0) {
            while (left >= 0 && s.charAt(left) != ' ') {
                left--; // 搜索首个空格
            }
            res.append(s.substring(left + 1, right + 1) + " "); // 添加单词
            while (left >= 0 && s.charAt(left) == ' ') {
                left--; // 跳过单词间空格
            }
            right = left; // right 指向下个单词的尾字符
        }
        return res.toString().trim();
    }

    public static void main(String[] args) {
        Offer58I offer58I = new Offer58I();
        String s = offer58I.reverseWords("   hello   hewl hhhfd   2324 ");
        System.out.printf(":" + s + ":");

    }
}
