package com.gjxaiou.advanced.day04;

import java.util.LinkedList;

public class ExpressionCompute {

    // 方法入口
    public static int getValue(String str) {
        return value(str.toCharArray(), 0)[0];
    }

    /**
     * 递归函数
     *
     * @param str
     * @param i   当前是从哪个位置开始的
     * @return 返回数组，长度为 2，arr[0] 表示计算结果，arr[1] 表示算到哪个位置，便于返回之后主过程知道从哪里继续开始计算
     */
    public static int[] value(char[] str, int i) {
        LinkedList<String> que = new LinkedList<String>();
        int pre = 0;
        int[] bra = null;
        // 到数组结尾或者遇到 ） 就停止返回结果
        while (i < str.length && str[i] != ')') {
            // 如果遇到数字要一直收集，因为上面使用 toCharArray()，会把一个完整的数字切分开
            if (str[i] >= '0' && str[i] <= '9') {
                pre = pre * 10 + str[i++] - '0';
                // 不是数字了，也不是 （，开始又规定不是结尾，不是 ），所以只能遇到运算符 + - * /
            } else if (str[i] != '(') {
                // 把形成的数放入栈中
                addNum(que, pre);
                // 放入当前的运算符
                que.addLast(String.valueOf(str[i++]));
                pre = 0;
                // 遇到了 （，当前位置为 i 位置
            } else {
                bra = value(str, i + 1);
                pre = bra[0];
                // 相当于从 ）后面一个位置开始计算
                i = bra[1] + 1;
            }
        }
        addNum(que, pre);
        return new int[]{getNum(que), i};
    }


    public static void addNum(LinkedList<String> que, int num) {
        if (!que.isEmpty()) {
            int cur = 0;
            // 如果栈的最上面是 +  或者 — ，则弹出再放入，即相当于不同
            String top = que.pollLast();
            if (top.equals("+") || top.equals("-")) {
                que.addLast(top);
                // 只能是乘除符号了
            } else {
                // 上面已经弹出最上面的符号了，在弹出一个就是数字值了
                cur = Integer.valueOf(que.pollLast());
                // 将传入的值和弹出的值进行乘除运算然后再次放入
                num = top.equals("*") ? (cur * num) : (cur / num);
            }
        }

        que.addLast(String.valueOf(num));
    }

    public static int getNum(LinkedList<String> que) {
        int res = 0;
        boolean add = true;
        String cur = null;
        int num = 0;
        while (!que.isEmpty()) {
            cur = que.pollFirst();
            if (cur.equals("+")) {
                add = true;
            } else if (cur.equals("-")) {
                add = false;
            } else {
                num = Integer.valueOf(cur);
                res += add ? num : (-num);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String exp = "48*((70-65)-43)+8*1";
        System.out.println(getValue(exp));

        exp = "4*(6+78)+53-9/2+45*8";
        System.out.println(getValue(exp));

        exp = "10-5*3";
        System.out.println(getValue(exp));

        exp = "-3*4";
        System.out.println(getValue(exp));

        exp = "3+1*4";
        System.out.println(getValue(exp));
    }
}
