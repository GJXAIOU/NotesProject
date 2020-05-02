package com.practice2020.kuaishou;

/**
 * @author GJXAIOU
 * @create 2020/04/12 16:48
 */
public class Main2 {
    /**
     * 返回无重复幂因子的 N进制完美数之和的所有幂因子
     *
     * @param R int整型
     * @param N int整型 N进制
     * @return int整型一维数组
     */

    // 首先获得该进制 N 的最大数

    public static int maxZhiShu = 0;

    public int[] GetPowerFactor(int R, int N) {
        maxZhiShu = R % N;

        int[] value = new int[maxZhiShu];
        for (int i = 1; i <= maxZhiShu; i++) {
            value[i] = i;
        }

        String temp1 = null;
        String temp = temp(1, R, R, N, temp1);
        int[] res = new int[maxZhiShu];
        int j = 0;
        for (int i = 0; i < temp.length(); i++) {
            if ((int) temp.charAt(i) != 0) {
                res[j++] = (int) temp.charAt(i);
            }
        }
        return res;
    }

    public static String temp(int index, int result, int remain, int N, String temp2) {

        int i = 0;
        String resString = null;
        if (remain == 0) {
            return temp2;
        }
        if ((index == maxZhiShu) && remain == 0) {
            return temp2;
        }
        if ((index == maxZhiShu) && remain != 0){
            return null;
        }
        // 不要该位的值

        resString += temp(index + 1, result, remain, N, temp2);
        resString += temp(index + 1, result,
                remain - (int) Math.pow(N, index), N,
                temp2 + String.valueOf(index));

        return temp2;
    }

    public static void main(String[] args) {
        Main2 main2 = new Main2();
        int[] ints = main2.GetPowerFactor(39, 3);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
