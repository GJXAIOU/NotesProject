package nowcoder.advanced.advanced_class_06;

import java.util.HashMap;

public class Code_01_CoinsWay {

    public static int coins1(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return process1(arr, 0, aim);
    }

    /**
     * @param arr
     * @param index ：可以任意自由使用 index 及其之后所有的钱
     * @param aim   ：目标钱数
     * @return ：方法数
     */
    public static int process1(int[] arr, int index, int aim) {
        int res = 0;
        // 如果到达数组最后一个位置，拿出一定金额之后 aim 值为 0，就说明是一种有效的划分，反之则说明该划分不行；
        // 所以最后该划分结果是否可行到最后一位之后才可以判断出来；
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int zhangshu = 0; arr[index] * zhangshu <= aim; zhangshu++) {
                // index 为当前货币金额，已经使用过了，从 index + 1 位置开始往后都可以使用；
                // aim - arr[index] * zhangshu 为后面需要凑齐的钱数；
                res += process1(arr, index + 1, aim - arr[index] * zhangshu);
            }
        }
        return res;
    }

    /**
     * 上述暴力方法问题： 200,100,50,10,5,2,1 aim = 1000；则选择 2 张 200,0 张 100，或者 0 张 200,4 张 100，都会需要在 50,
     * 10,5,2,1 中选择得到 600 元，造成重复计算。
     * 同时该问题为无后效性问题，即让后面一串钱搞定 600 元和前面怎么到的 600 元这个状态无关。
     * 一般面试都是无后效性问题。
     */

    // 暴力修改（修改上面的递归方法），因为 index 和 aim 确定，最后返回值结果就确定了，所以计算完之后将该状态和其返回值保存下来可以下次使用
    // String 格式为："index_aim"，Integer 为该种情况下对应的返回值。
    // 使用 map 做一个缓存功能
    public static HashMap<String, Integer> map = new HashMap<>();

    public static int processMap(int[] arr, int index, int aim) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int zhangshu = 0; arr[index] * zhangshu <= aim; zhangshu++) {
                int nextAim = aim - arr[index] * zhangshu;
                String key = String.valueOf(index + 1) + "_" + String.valueOf(nextAim);
                if (map.containsKey(key)) {
                    res += map.get(key);
                } else {
                    res += processMap(arr, index + 1, nextAim);
                }
            }
        }
        map.put(String.valueOf(index) + "_" + String.valueOf(aim), res);
        return res;
    }


    public static int coinsOther(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        return processOther(arr, arr.length - 1, aim);
    }

    public static int processOther(int[] arr, int index, int aim) {
        int res = 0;
        if (index == -1) {
            res = aim == 0 ? 1 : 0;
        } else {
            for (int i = 0; arr[index] * i <= aim; i++) {
                res += processOther(arr, index - 1, aim - arr[index] * i);
            }
        }
        return res;
    }

    public static int coins2(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] map = new int[arr.length + 1][aim + 1];
        return process2(arr, 0, aim, map);
    }

    public static int process2(int[] arr, int index, int aim, int[][] map) {
        int res = 0;
        if (index == arr.length) {
            res = aim == 0 ? 1 : 0;
        } else {
            int mapValue = 0;
            for (int i = 0; arr[index] * i <= aim; i++) {
                mapValue = map[index + 1][aim - arr[index] * i];
                if (mapValue != 0) {
                    res += mapValue == -1 ? 0 : mapValue;
                } else {
                    res += process2(arr, index + 1, aim - arr[index] * i, map);
                }
            }
        }
        map[index][aim] = res == 0 ? -1 : res;
        return res;
    }

    public static int coins3(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        int num = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                num = 0;
                for (int k = 0; j - arr[i] * k >= 0; k++) {
                    num += dp[i - 1][j - arr[i] * k];
                }
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    public static int coins4(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < arr.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 1; arr[0] * j <= aim; j++) {
            dp[0][arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length - 1][aim];
    }

    public static int coins5(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }
        int[] dp = new int[aim + 1];
        for (int j = 0; arr[0] * j <= aim; j++) {
            dp[arr[0] * j] = 1;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 1; j <= aim; j++) {
                dp[j] += j - arr[i] >= 0 ? dp[j - arr[i]] : 0;
            }
        }
        return dp[aim];
    }

    public static void main(String[] args) {
        int[] coins = {10, 5, 1, 25};
        int aim = 2000;

        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        System.out.println(coins1(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coinsOther(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        aim = 20000;

        start = System.currentTimeMillis();
        System.out.println(coins2(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins3(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins4(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

        start = System.currentTimeMillis();
        System.out.println(coins5(coins, aim));
        end = System.currentTimeMillis();
        System.out.println("cost time : " + (end - start) + "(ms)");

    }

}
