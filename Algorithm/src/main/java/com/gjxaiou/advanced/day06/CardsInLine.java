package com.gjxaiou.advanced.day06;

/**
 * @author GJXAIOU
 */
public class CardsInLine {
    // 方法一：暴力递归
    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(first(arr, 0, arr.length - 1), second(arr, 0, arr.length - 1));
    }

    // first 表示在剩余的 beginIndex ~ endIndex 中，先拿者会得到最大分数。
    public static int first(int[] arr, int beginIndex, int endIndex) {
        // 如果只剩一个，则只能拿这个了，这就是先拿者能在该步骤获得的最大值
        if (beginIndex == endIndex) {
            return arr[beginIndex];
        }
        // 如果剩余的不止一个，能获得的最大值就是在 先着拿开始位置 + 后者拿剩下的 和 先者拿末尾位置 + 后者拿剩下的 中的最大的一个
        return Math.max(arr[beginIndex] + second(arr, beginIndex + 1, endIndex),
                arr[endIndex] + second(arr, beginIndex, endIndex - 1));
    }

    // second 表示在剩余的 beginIndex ~ endIndex 中，后拿者会得到最大分数。
    public static int second(int[] arr, int beginIndex, int endIndex) {
        // 如果只有一张牌，后者就没有的拿了，返回 0
        if (beginIndex == endIndex) {
            return 0;
        }
        // 假设先拿者拿到了 beginIndex]，对于剩下的 (beginIndex + 1) ~ endIndex 之间的纸牌，后拿者就转变身份成了先拿者，这一过程可以交给
        //`first()`来处理。
        // 假设先拿者拿到了 endIndex，对于剩下的 `beginIndex ~ (endIndex - 1)
        //`之间的纸牌，后拿者就转变身份成了先拿者，这一过程可以交给`first()`来处理。
        // 这里取两种情况中**结果较小**的一种，是因为这两种情况是我们假设的，但先拿者 A 绝顶聪明，他的选择肯定会让后拿者尽可能拿到更小的分数
        return Math.min(first(arr, beginIndex + 1, endIndex), first(arr, beginIndex,
                endIndex - 1));
    }

    // 方法二：动态规划
    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] first = new int[arr.length][arr.length];
        int[][] second = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            // 首先对对角线上元素赋 base 值
            first[j][j] = arr[j];
            // 填充其他元素
            for (int i = j - 1; i >= 0; i--) {
                first[i][j] = Math.max(arr[i] + second[i + 1][j], arr[j] + second[i][j - 1]);
                second[i][j] = Math.min(first[i + 1][j], first[i][j - 1]);
            }
        }
        return Math.max(first[0][arr.length - 1], second[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 1, 3};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
    }
}
