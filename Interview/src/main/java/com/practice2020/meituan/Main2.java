package com.practice2020.meituan;
import java.util.Scanner;

/**
 * @author GJXAIOU
 */
public class Main2 {
    // 本质就是找循环周期， 即 x 的循环周期
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int m = scanner.nextInt();
        int x = scanner.nextInt();
        int temp = 0;
        int num = 0;
        temp = (a * x + b) % m;
        while (num == 1 || temp != x) {
            x = (a * x + b) % m;
            num++;
        }
        System.out.println(num - 1);
    }
}
