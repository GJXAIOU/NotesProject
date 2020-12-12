package com.gjxaiou.advanced.day03;

public class MaxEOR {

    /**
     * 解法一：最暴力解法：O(N^3)
     */
    public static int getMaxE(int[] arr) {
        int max = Integer.MIN_VALUE;
        // 分别计算 0 ~ i，1 ~ i。。。i ~ i 的异或和
        // 以 i 为结尾的
        for (int i = 0; i < arr.length; i++) {
            // start 到 i 的最大异或和
            for (int start = 0; start <= i; start++) {
                int res = 0;
                // 遍历start---i针对上面的每一个子数组求异或和
                for (int k = start; k <= i; k++) {
                    res ^= arr[k];
                }
                max = Math.max(max, res);
            }
        }
        return max;
    }


    /**
     * 方法二：优化方法：O（N^2）
     */
    // 异或运算满足交换律与结合律： 若 E1 ^ E2 = E3，则 E1 = E2 ^ E3，E2 = E1 ^ E3；
    public static int getMaxE2(int[] arr) {
        int max = Integer.MIN_VALUE;
        // 准备一个辅助数组，里面放置
        int[] dp = new int[arr.length];
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            // eor 每次都异或新数，最终得到 eor 就是 0 ~ i 的异或和
            eor ^= arr[i];
            max = Math.max(max, eor);
            // 下面计算 start ~ i 的计算结果，例如 2 ~ i 的结果为 0 ~ i 异或结果再异或 0 ~ 2 位置上值：整个遍历得到了以i为结尾的最大异或和
            for (int start = 1; start <= i; start++) {
                // curEor 就是 start ~ i 的异或结果，0---i的异或和^0---start的异或和==start-i的异或和
                int curEor = eor ^ dp[start - 1];
                max = Math.max(max, curEor);
            }
            //dp[i]存放着0到i的异或和
            dp[i] = eor;
        }
        return max;
    }


    /**
     * 解法三：前缀树 O（N）
     */
    public static class TrieNode {
        // 因为是前缀树，所以只有通向 0 或者 1 的路
        public TrieNode[] nexts = new TrieNode[2];
    }

    public static class NumTrie {
        public TrieNode head = new TrieNode();

        public void add(int num) {
            TrieNode cur = head;
            // 因为加入的 int 类型，依次判断每一位的值，然后建立前缀树
            for (int move = 31; move >= 0; move--) {
                // 获取的是 int 类型符号位数，并且和 1 相与，如果符号位上为 0 结果为 0，反之如果为 1 则结果为 1；
                int path = (num >> move) & 1;
                // 当前结点走向 path 的路是否为空，如果没有就新建
                cur.nexts[path] = cur.nexts[path] == null ? new TrieNode() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        // num 为从 0 ~ i 的异或结果
        public int maxXor(int num) {
            TrieNode cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                // 依次从最高位开始提取每一位上数
                int path = (num >> move) & 1;
                // 第一个符号为要选路，因为符号位应该走能保证异或之后值为 0 的路；符号位为 0 则应该选择 0 这条路，返回选择 1 这条路；
                // 如果不是符号位，因为保证最大，所以要选择能保证异或结果为 1 的路，所以选择的路值和原来值相反。
                int best = move == 31 ? path : (path ^ 1);
                // 如果有走向 best 的路则走 best 路，如果没有只能走另一条路
                best = cur.nexts[best] != null ? best : (best ^ 1);
                // 设置答案中每一位的值
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }

    }

    public static int maxXorSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int max = Integer.MIN_VALUE;
        int eor = 0;
        NumTrie numTrie = new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            // eor 是 0 ~ i 异或结果
            eor ^= arr[i];
            max = Math.max(max, numTrie.maxXor(eor));
            numTrie.add(eor);
        }
        return max;
    }
}
