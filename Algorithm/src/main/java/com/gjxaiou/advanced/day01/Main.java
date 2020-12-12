package com.gjxaiou.advanced.day01;

import java.util.*;

/**
 * 牛客上左神书上题目的练习代码
 * @Author GJXAIOU
 * @Date 2020/8/13 23:45
 */
public class Main {
    public static void main1(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N > 0) {
            int[] inputArray = new int[N + 2];
            inputArray[0] = 1;
            if (N == 1) {
                System.out.println(1);
                return;
            }
            for (int i = 1; i < inputArray.length - 1; i++) {
                inputArray[i] = scanner.nextInt();
            }
            inputArray[N + 1] = 1;

            System.out.println(help(inputArray, 1, N));
        }
    }

    public static int help(int[] inputArray, int left, int right) {
        if (left == right) {
            return inputArray[left] * inputArray[left - 1] * inputArray[right + 1];
        }
        int max = Integer.MIN_VALUE;
        max =
                Math.max(help(inputArray, left + 1, right) + inputArray[left] * inputArray[left - 1] * inputArray[right + 1], help(inputArray, left, right - 1) + inputArray[left - 1] * inputArray[right] * inputArray[right + 1]);
        for (int i = left + 1; i < right; i++) {
            max = Math.max(max,
                    help(inputArray, left, i - 1) + help(inputArray, i + 1, right) + inputArray[i] * inputArray[left - 1] * inputArray[right + 1]);
        }
        return max;
    }

    // 方案二：dp
    public static void main2(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N > 0) {
            int[] inputArray = new int[N + 2];
            inputArray[0] = 1;
            if (N == 1) {
                System.out.println(1);
                return;
            }
            for (int i = 1; i < inputArray.length - 1; i++) {
                inputArray[i] = scanner.nextInt();
            }
            inputArray[N + 1] = 1;

            int[][] dp = new int[N + 2][N + 2];
            for (int i = 1; i < N + 1; i++) {
                dp[i][i] = inputArray[i - 1] * inputArray[i] * inputArray[i + 1];
            }


            for (int L = N; L >= 1; L--) {
                for (int R = L + 1; R <= N; R++) {
                    int finalLeft =
                            dp[L + 1][R] + inputArray[L] * inputArray[L - 1] * inputArray[R + 1];
                    int finalRight =
                            dp[L][R - 1] + inputArray[R] * inputArray[L - 1] * inputArray[R + 1];
                    dp[L][R] = Math.max(finalLeft, finalRight);
                    for (int i = L + 1; i < R; i++) {
                        dp[L][R] = Math.max(dp[L][R],
                                dp[L][i - 1] + dp[i + 1][R] + inputArray[i] * inputArray[L - 1] * inputArray[R + 1]);
                    }
                }
            }
            System.out.println(dp[1][N]);
        } else {
            System.out.println(0);
        }
    }

    public static void main3(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N > 0) {
            int[] inputArray = new int[N];
            List<Integer> resList = new ArrayList<>();
            for (int i = 0; i < inputArray.length; i++) {
                inputArray[i] = scanner.nextInt();
            }
            int[] dp = new int[N];

            for (int i = 0; i < inputArray.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (inputArray[j] < inputArray[i]) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int maxLen = 1;
            int index = 0;
            for (int i = 0; i < dp.length; i++) {
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                    index = i;
                }
            }

            resList.add(inputArray[index]);
            for (int i = index; i >= 0; i--) {
                if (inputArray[i] < inputArray[index] && dp[i] == dp[index] - 1) {
                    resList.add(inputArray[i]);
                    index = i;
                }
            }

            for (Integer integer : resList) {
                System.out.println(integer);
            }
        } else {
            System.out.println(0);
        }
    }


    static class Node {
        int length;
        int wight;

        Node(int length, int wight) {
            this.length = length;
            this.wight = wight;
        }
    }

    static class MyCompactor implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.length == o2.length ? o2.wight - o1.wight : o1.length - o2.length;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if (N > 0) {
            Node[] inputArray = new Node[N];
            for (int i = 0; i < inputArray.length; i++) {
                inputArray[i] = new Node(scanner.nextInt(), scanner.nextInt());
            }

            Arrays.sort(inputArray, new MyCompactor());


            ArrayList<Node> resList = new ArrayList<>();
            int[] dp = new int[N];

            for (int i = 0; i < inputArray.length; i++) {
                dp[i] = 1;
                for (int j = 0; j < i; j++) {
                    if (inputArray[j].wight < inputArray[i].wight) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }
            }
            int maxLen = 1;
            int index = 0;
            for (int i = 0; i < dp.length; i++) {
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                    index = i;
                }
            }

            resList.add(inputArray[index]);
            for (int i = index; i >= 0; i--) {
                if (inputArray[i].wight < inputArray[index].wight && dp[i] == dp[index] - 1) {
                    resList.add(inputArray[i]);
                    index = i;
                }
            }

            System.out.println(resList.size());
        } else {
            System.out.println(0);
        }
    }

}
