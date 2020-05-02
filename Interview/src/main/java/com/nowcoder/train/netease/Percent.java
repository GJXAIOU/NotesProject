package com.nowcoder.train.netease;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/4/5 21:20
 * 网易2020校招笔试- 系统开发/研发工程师（提前批）
 * 小易是班级的英语课代表, 他开发了一款软件开处理他的工作。
 * 小易的软件有一个神奇的功能，能够通过一个百分数来反应你的成绩在班上的位置。“成绩超过班级 ...% 的同学”。
 * 设这个百分数为 p，考了 s 分，则可以通过以下式子计算得出 p：
 * p = ( 分数不超过 s 的人数 - 1)  班级总人数
 * 突然一天的英语考试之后，软件突然罢工了，这可忙坏了小易。成绩输入这些对于字写得又快又好的小易当然没有问题，但是计算这些百分数……这庞大的数据量吓坏了他。
 * 于是他来找到你，希望他编一个程序模拟这个软件：给出班级人数 n，以及每个人的成绩，请求出某几位同学的百分数。
 * <p>
 * 输入描述:
 * 第一行一个整数 n，表示班级人数。
 * 第二行共 n 个自然数，第 i 个数表示第 i 位同学的成绩 a_i。
 * 第三行一个整数 q，表示询问的次数。
 * 接下来 q 行，每行一个数 x，表示询问第 x 位同学的百分数。
 * <p>
 * <p>
 * 输出描述:
 * 输出应有 q 行，每行一个百分数，对应每一次的询问。
 * <p>
 * 为了方便，不需要输出百分号，只需要输出百分号前的数字即可。四舍五入保留六位小数即可。
 * <p>
 * 输入例子1:
 * 3
 * 100 98 87
 * 3
 * 1
 * 2
 * 3
 * <p>
 * 输出例子1:
 * 66.666667
 * 33.333333
 * 0.000000
 */
public class Percent {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 总分数个数（人数）
        int totalNum = scanner.nextInt();
        int[] score = new int[totalNum];
        int i = 0;
        while (i < totalNum) {
            score[i] = scanner.nextInt();
            i++;
        }

        int ansNum = scanner.nextInt();
        int[] ansIndex = new int[ansNum];
        int j = 0;
        while (j < ansNum) {
            // 因为考虑到数组从0 开始
            ansIndex[j] = scanner.nextInt() - 1;
            j++;
        }

        int[] scoreSort = Arrays.copyOf(score, score.length);


        // 是 java.util.Comparator()，从小到大排序
        Arrays.sort(scoreSort);

        long[] ans = new long[ansNum];
        int k = 0;
        while (k < ansNum) {
            // 找到要查询的第一个序号的分数
            int findScore = score[ansIndex[k]];

            int index = 0;
            for (int i1 = 0; i1 < scoreSort.length; i1++) {
                // 找到最右边等于要查的值的位置，因为可能几个人分数一样
                if (scoreSort[i1] == findScore) {
                    index  = i1;
                    while (index < scoreSort.length - 1) {
                        if (scoreSort[index + 1] == findScore) {
                            index++;
                        } else {
                            break;
                        }
                    }

                }
            }
            double d = (index) * 100 / totalNum;
            DecimalFormat df = new DecimalFormat("00.000000");
            ans[k] = Long.parseLong(df.format(d));
            k++;
        }
        for (long an : ans) {
            System.out.println(an);
        }


    }
}



