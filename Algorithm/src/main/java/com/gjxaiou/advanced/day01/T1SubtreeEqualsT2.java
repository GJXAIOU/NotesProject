package com.gjxaiou.advanced.day01;

/**
 * 判断树 1 的某棵子树是否包含树 2
 *
 * @author GJXAIOU
 */
public class T1SubtreeEqualsT2 {

    public static class TreeNode {
        public int value;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int data) {
            this.value = data;
        }
    }

    // 首先对两棵树进行序列化，然后就可以按照字符串进行判断
    public static boolean isSubtree(TreeNode t1, TreeNode t2) {
        String t1Str = treeSerialToString(t1);
        String t2Str = treeSerialToString(t2);
        return getIndexOf(t1Str, t2Str) != -1;
    }

    // 将树结构序列化为字符串，这里使用前序遍历
    public static String treeSerialToString(TreeNode head) {
        if (head == null) {
            return "#!";
        }
        String res = head.value + "!";
        res += treeSerialToString(head.left);
        res += treeSerialToString(head.right);
        return res;
    }

    // 按照 KMP 算法即可
    public static int getIndexOf(String str, String match) {
        if (str == null || match == null || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }
        char[] strArray = str.toCharArray();
        char[] matchArray = match.toCharArray();
        int[] nextArr = getNextArray(matchArray);
        int strIndex = 0;
        int matchIndex = 0;
        while (strIndex < strArray.length && matchIndex < matchArray.length) {
            if (strArray[strIndex] == matchArray[matchIndex]) {
                strIndex++;
                matchIndex++;
            } else if (nextArr[matchIndex] == -1) {
                strIndex++;
            } else {
                matchIndex = nextArr[matchIndex];
            }
        }
        return matchIndex == matchArray.length ? strIndex - matchIndex : -1;
    }

    public static int[] getNextArray(char[] matchArray) {
        if (matchArray.length == 1) {
            return new int[]{-1};
        }
        int[] nextArr = new int[matchArray.length];
        nextArr[0] = -1;
        nextArr[1] = 0;
        int curIndex = 2;
        int cn = 0;
        while (curIndex < nextArr.length) {
            if (matchArray[curIndex - 1] == matchArray[cn]) {
                nextArr[curIndex++] = ++cn;
            } else if (cn > 0) {
                cn = nextArr[cn];
            } else {
                nextArr[curIndex++] = 0;
            }
        }
        return nextArr;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        t1.left = new TreeNode(2);
        t1.right = new TreeNode(3);
        t1.left.left = new TreeNode(4);
        t1.left.right = new TreeNode(5);
        t1.right.left = new TreeNode(6);
        t1.right.right = new TreeNode(7);
        t1.left.left.right = new TreeNode(8);
        t1.left.right.left = new TreeNode(9);

        TreeNode t2 = new TreeNode(2);
        t2.left = new TreeNode(4);
        t2.left.right = new TreeNode(8);
        t2.right = new TreeNode(5);
        t2.right.left = new TreeNode(9);

        System.out.println(isSubtree(t1, t2));
    }
}
