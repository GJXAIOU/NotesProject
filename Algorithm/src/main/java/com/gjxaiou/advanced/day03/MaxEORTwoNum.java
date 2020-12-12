package com.gjxaiou.advanced.day03;

/**
 * @Author GJXAIOU
 * @Date 2020/8/9 10:56
 */
public class MaxEORTwoNum {
    //前缀树
    public static class TrieNode {
        TrieNode[] nexts = new TrieNode[2];
    }

    public static class Trie {
        private TrieNode head;

        Trie() {
            head = new TrieNode();
        }

        public void insert(int num) {
            TrieNode cur = head;
            for (int move = 31; move >= 0; move--) {
                int path = num >> move & 1;
                if (cur.nexts[path] == null) {
                    cur.nexts[path] = new TrieNode();
                }
                cur = cur.nexts[path];
            }
        }

        public int getMaxXorNum(int value) {
            TrieNode cur = head;
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (value >> move) & 1;
                if (move == 31) {
                    if (cur.nexts[path] == null) {
                        path = 1 ^ path;//~path
                    }
                } else {
                    if (cur.nexts[1 ^ path] != null) {
                        path = 1 ^ path;//~path
                    }
                }
                cur = cur.nexts[path];
                res = res | ((path ^ path) << move);
            }
            return res;
        }
    }

    public static int findMaximumXOR(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Trie tree = new Trie();
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            tree.insert(nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            result = Math.max(result, tree.getMaxXorNum(nums[i]));
        }
        return result;
    }

}
