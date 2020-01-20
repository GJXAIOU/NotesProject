package array.easy;

import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/1/20 19:16
 */
public class LeetCode88 {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // 思路：首相将 nums1 中原来元素复制到新的数组中，然后双指针分别遍历 nums1 新数组和 nums2 中，那个小哪个就放入 nums1
        //中，如果有一个遍历结束，则另一个的剩下所有元素都放入 nums1 中。

        int[] copyNums1 = new int[m];
        // 将 Nums1 从 0 位置开始复制长度为 m，然后复制到 copyNums1 数组中，从 0 下标开始
        System.arraycopy(nums1, 0, copyNums1, 0, m);
        // 两个指针分别指向 copyNums1 和 nums2 开头
        int p1 = 0;
        int p2 = 0;
        // i 为 Nums1 下标
        int i = 0;

        // 当两个数组都有值的时候
        while ((p1 < m) && (p2 < n)) {
            if (copyNums1[p1] < nums2[p2]) {
                nums1[i] = copyNums1[p1];
                p1++;
            } else {
                nums1[i] = nums2[p2];
                p2++;
            }
            i++;
        }
        // 上面 while 语句等价于：
//        while ((p1 < m) && (p2 < n)) {
//            nums1[i++] = (copyNums1[p1] < nums2[p2]) ? copyNums1[p1++] : nums2[p2++];
//        }

        // 如果有一个到头的时候，则说明另一个没有到头
        // 如果 p1 没有到头，即 p2 到头了
        if (p1 < m) {
            System.arraycopy(copyNums1, p1, nums1, p1 + p2, m + n - p1 - p2);
        }
        if (p2 < n) {
            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
        }
    }

    // --------- 对数器  ----------
    // 绝对正确的方法
    public void absoluteMerge(int[] nums1, int m, int[] nums2, int n) {
        System.arraycopy(nums2, 0, nums1, m, n);
        Arrays.sort(nums1, 0, m + n);
    }

    // 随机数产生器，分别产生长度为 m, n 的随机整数数组
    public int[] generateRandom(int length, int trueLength) {
        if (length < trueLength) {
            return null;
        }
        int[] randomValue = new int[length];
        for (int i = 0; i < trueLength; i++) {
            randomValue[i] = (int) (Math.random() * 100);
        }
        Arrays.sort(randomValue, 0, trueLength);
        return randomValue;
    }

    // 比较器
    public static boolean isEquals(int[] x, int[] y) {
        if (x.length != y.length) {
            return false;
        }
        for (int i = 0; i < x.length; i++) {
            if (x[i] != y[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // 生成数组
        LeetCode88 leetCode88 = new LeetCode88();
        int mums1Length = 100;
        int m = 60;
        int n = 35;
        int[] nums1 = leetCode88.generateRandom(mums1Length, m);
        int[] copyNums1 = new int[mums1Length];
        System.arraycopy(nums1, 0, copyNums1, 0, mums1Length);
        int[] nums2 = leetCode88.generateRandom(n, n);

        System.out.println("\n 生成测试用例 nums1：");
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println("\n 生成测试用例 nums2：");
        for (int i = 0; i < nums2.length; i++) {
            System.out.print(nums2[i] + " ");
        }


        System.out.println("\n 测试方法的结果：");
        leetCode88.merge(nums1, m, nums2, n);
        for (int i = 0; i < nums1.length; i++) {
            System.out.print(nums1[i] + " ");
        }

        System.out.println("\n 绝对正确的结果：");
        leetCode88.absoluteMerge(copyNums1, m, nums2, n);
        for (int i = 0; i < copyNums1.length; i++) {
            System.out.print(copyNums1[i] + " ");
        }

        System.out.println("\n 两者结果是否相等：");
        boolean equals = isEquals(nums1, copyNums1);
        System.out.println(equals);
    }
}
