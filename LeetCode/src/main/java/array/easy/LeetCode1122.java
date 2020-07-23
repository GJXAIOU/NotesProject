package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/22 15:42
 */
public class LeetCode1122 {
    // 方法一：
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        // 记录 arr1 中每个元素出现的次数
        int[] arr1Count = new int[1001];

        int[] res = new int[arr1.length];

        for (int i = 0; i < arr1.length; i++) {
            arr1Count[arr1[i]]++;
        }

        int index = 0;
        for (int i = 0; i < arr2.length; i++) {
            while (arr1Count[arr2[i]] > 0) {
                res[index++] = arr2[i];
                arr1Count[arr2[i]]--;
            }
        }

        // 剩余元素放入 res 数组中
        for (int i = 0; i < 1001; i++) {
            while (arr1Count[i] > 0) {
                res[index++] = i;
                arr1Count[i]--;
            }
        }
        return res;
    }
}
