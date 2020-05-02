package array.easy;

import java.util.HashMap;

/**
 * @Author GJXAIOU
 * @Date 2020/1/19 19:10
 */
public class LeetCode13 {
    public int romanToInt(String s) {
        // 将所有的可能性存储到 HashMap 中，然后遍历 String 取出值挨个比较
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            char current = s.charAt(i);
            char next = s.charAt(i + 1);
            int currentValue = map.get(current);
            int nextValue = map.get(next);
// 如果当前值小于后面的值，并且后面的值是前面的 5 或者 10 倍，则当前值变为负数加上去
            if (currentValue < nextValue && (currentValue == nextValue / 5 || currentValue == nextValue / 10)) {
                res -= currentValue;
            } else {
                res += currentValue;
            }
        }
        // 加上字符串最后一个值
        res += map.get(s.charAt(s.length() - 1));
        return res;
    }

    //-------------对数器----------
    // 1.绝对正确的方法
    public int absoluteRomanToInt(String s) {
        int res = 0;
        // 首先遍历一遍字符串，然后将每个罗马字母对应的值加上去
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case 'I':
                    res += 1;
                    break;
                case 'V':
                    res += 5;
                    break;
                case 'X':
                    res += 10;
                    break;
                case 'L':
                    res += 50;
                    break;
                case 'C':
                    res += 100;
                    break;
                case 'D':
                    res += 500;
                    break;
                case 'M':
                    res += 1000;
                    break;
            }
        }

        // 然后减去特殊值形成的差
        for (int j = 0; j < s.length() - 1; j++) {
            switch ("" + s.charAt(j) + s.charAt(j + 1)) {
                case "IV":
                case "IX":
                    res -= 2;
                    break;
                case "XL":
                case "XC":
                    res -= 20;
                    break;
                case "CD":
                case "CM":
                    res -= 200;
                    break;
            }
        }
        return res;
    }

    // 2.随机产生器，这里随机罗马数字不易产生，自行输入
    // 3.相等方法因为是整数比较，省略

    public static void main(String[] args) {
        String s = "MCMXCLVIII";
        LeetCode13 leetCode13 = new LeetCode13();
        int i = leetCode13.romanToInt(s);
        int j = leetCode13.absoluteRomanToInt(s);
        System.out.println("i: " + i + "j: " + j);
        System.out.println(i == j);
    }
}
