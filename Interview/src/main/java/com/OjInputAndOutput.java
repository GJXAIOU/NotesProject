package com;

import sun.util.resources.sq.CalendarData_sq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @Author GJXAIOU
 * @Date 2020/3/29 16:30
 */
public class OjInputAndOutput<duishuqi> {
    /**
     * 1.输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据包括多组
     */
    public void method1() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            // 这里执行具体语句
            System.out.println(a + b);
        }
    }

    /**
     * 输入第一行包括一个数据组数t(1 <= t <= 100)
     * 接下来每行包括两个正整数a,b(1 <= a, b <= 10^9)
     * <p>
     * 示例：
     * 2
     * 1 5
     * 10 20
     */

    public void method2() {
        Scanner scanner = new Scanner(System.in);
        int lineNumber = scanner.nextInt();
        for (int i = 0; i < lineNumber; i++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            // 这里执行具体语句
        }
    }


    /**
     * 输入包括两个正整数a,b(1 <= a, b <= 10^9),输入数据有多组, 如果输入为0 0则结束输入
     * <p>
     * 示例：
     * 1 5
     * 10 20
     * 0 0
     */
    public void method3() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            if ((a == 0) && (b == 0)) {
                break;
            } else {
                // 执行语句

            }
        }
    }


    /**
     * 输入的第一行包括一个正整数t(1 <= t <= 100), 表示数据组数。
     * 接下来t行, 每行一组数据。
     * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
     * 接下来n个正整数, 即需要求和的每个正整数。
     */

    public void method4() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        ArrayList<Integer> ansList = new ArrayList(t);
        for (int i = 0; i < t; i++) {
            int n = scanner.nextInt();
            for (int j = 0; j < n; j++) {
                int ans = 0;
                ans += scanner.nextInt();
                ansList.add(ans);
            }
        }
        for (Integer integer : ansList) {
            System.out.println(integer);
        }
    }

    /**
     * 输入数据有多组, 每行表示一组输入数据。
     * 每行的第一个整数为整数的个数n(1 <= n <= 100)。
     * 接下来n个正整数, 即需要求和的每个正整数。
     * <p>
     * 示例：
     * 4 1 2 3 4
     * 5 1 2 3 4 5
     */
    public void method5() {
        Scanner scanner = new Scanner(System.in);
        for (; ; ) {
            int n = scanner.nextInt();
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += scanner.nextInt();
            }
            System.out.println(ans);
        }
    }


    /**
     * 输入数据有多组, 每行表示一组输入数据。
     * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
     * <p>
     * 示例：
     * 1 2 3
     * 4 5
     * 0 0 0 0 0
     */
    public void method6() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String[] inputStringArray = scanner.nextLine().split(" ");
            int sum = 0;
            for (int i = 0; i < inputStringArray.length; i++) {
                sum += Integer.parseInt(inputStringArray[i]);
            }
            System.out.println(sum);
        }
    }



}
