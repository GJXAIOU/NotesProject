package com.practice2020.netease;

import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/4/7 18:35
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int n = scanner.nextInt();
            long[] arr = new long[n];
            long a1 = scanner.nextLong();
            long a2 = scanner.nextLong();
            long min = a2 - a1;
            for (int i = 1; i < n; i++){
                arr[i] = a2 - a1;
                a1 = a2;
                a2 = scanner.nextLong();
                if (min > arr[i]){
                    min = arr[i];
                }
                long res = 0;
                for (int j = 1; j < n; j++){
                    long temp = gys(min, arr[i]);
                    if (i == 1){
                        res = temp;
                    }else if (res > temp){
                        res = temp;
                    }
                    if (res <= 0){
                        System.out.println(-1);
                    }else {
                       // System.out.println(res);
                    }
                }
            }
        }

    }

    public static long gys(long a, long b){
        while (b % a != 0){
            long temp = b % a;
            b = a;
            a = temp;
        }
        return a;
    }
}
