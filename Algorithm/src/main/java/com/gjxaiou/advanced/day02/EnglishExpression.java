package com.gjxaiou.advanced.day02;

public class EnglishExpression {

    // 如果是 1- 19，直接取值；可以通过枚举的方式,将所有19个可能性放入到一个String数组中
    public static String num1To19(int num) {
        if (num < 1 || num > 19) {
            return "";
        }
        String[] names = {"One ", "Two ", "Three ", "Four ", "Five ", "Six ",
                "Seven ", "Eight ", "Nine ", "Ten ", "Eleven ", "Twelve ",
                "Thirteen ", "Fourteen ", "Fifteen ", "Sixteen ", "Sixteen ",
                "Eighteen ", "Nineteen "};
        return names[num - 1];
    }

    /**
     * 当我们传入16是,就找出这个数组中16-1,下标为15的表达.十分简单,现在完成了1---19的表达,然后我们来完成1---99的表达,我们通过/运算得到十位,用一个数组来承担,
     * 然后通过%运算得到低位,然后使用num1to19来表达
     */
    public static String num1To99(int num) {
        if (num < 1 || num > 99) {
            return "";
        }
        if (num < 20) {
            return num1To19(num);
        }
        int high = num / 10;
        String[] tyNames = {"Twenty ", "Thirty ", "Forty ", "Fifty ",
                "Sixty ", "Seventy ", "Eighty ", "Ninety "};
        return tyNames[high - 2] + num1To19(num % 10);
    }

    // 依次类推,我们现在知道了1---99的表达,然后我们通过/和%运算就可以得到1---999的表达了,但是要注意,在中间加上,"Hundred".
    public static String num1To999(int num) {
        if (num < 1 || num > 999) {
            return "";
        }
        if (num < 100) {
            return num1To99(num);
        }
        int high = num / 100;
        return num1To19(high) + "Hundred " + num1To99(num % 100);
    }

    // 主函数
    public static String getNumEngExp(int num) {
        // 第一步判断num是不是0,如果是0的话直接返回 Zero
        if (num == 0) {
            return "Zero";
        }
        String res = "";
        // 第二步判断是不是负数,如果num<0,我们家在操作之前加上negative,
        if (num < 0) {
            res = "Negative, ";
        }
        // 重点来了,我们这儿为什么要将Integer.MIN_VALUE单另的拿出来做判断.因为如果不做处理的话,将Integer.MIN_VALUE转化成整数的话,就会溢出
        if (num == Integer.MIN_VALUE) {
            res += "Two Billion, ";
            num %= -2000000000;
        }
        num = Math.abs(num);
        int high = 1000000000;
        int highIndex = 0;
        String[] names = {"Billion", "Million", "Thousand", ""};
        while (num != 0) {
            int cur = num / high;
            num %= high;
            if (cur != 0) {
                res += num1To999(cur);
                res += names[highIndex] + (num == 0 ? " " : ", ");
            }
            high /= 1000;
            highIndex++;
        }
        return res;
    }

    /**
     * 测试程序
     */
    public static int generateRandomNum() {
        boolean isNeg = Math.random() > 0.5 ? false : true;
        int value = (int) (Math.random() * Integer.MIN_VALUE);
        return isNeg ? value : -value;
    }

    public static void main(String[] args) {
        System.out.println(getNumEngExp(0));
        System.out.println(getNumEngExp(Integer.MAX_VALUE));
        System.out.println(getNumEngExp(Integer.MIN_VALUE));
        int num = generateRandomNum();
        System.out.println(num);
        System.out.println(getNumEngExp(num));
    }
}
