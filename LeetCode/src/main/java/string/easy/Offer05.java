package string.easy;

/**
 * @Author GJXAIOU
 * @Date 2020/2/24 13:56
 */
public class Offer05 {

    public String replaceSpace(String s) {
        if (s.length() == 0) {
            return s;
        }
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                count++;
            }
        }
        // 空格占 1 个字符，%20 占 3 个字符，所以有一个空格结果中需要加两个位置
        char[] resArray = new char[s.length() + count * 2];
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp == ' ') {
                resArray[j++] = '%';
                resArray[j++] = '2';
                resArray[j++] = '0';
            } else {
                resArray[j++] = temp;
            }

        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < resArray.length; i++) {
            res.append(resArray[i]);
        }

        return res.toString();
    }

    // 方法二：
    public String replaceSpace2(String s) {
        StringBuilder res = new StringBuilder();
        for (Character c : s.toCharArray()) {
            if (c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }

}
