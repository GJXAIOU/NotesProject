package nowcoder.advanced.advanced_class_06;

import java.util.HashMap;

public class Code_05_LongestSubarrayLessSumAwesomeSolution {

    public static int maxLengthAwesome(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        // 分别对应 min_sum 和 min_sum_index
        int[] sums = new int[arr.length];
        int[] ends = new int[arr.length];
        // 最后一个元素的 min_sum 就是自己，min_sum_index 也是自己的下标
        sums[arr.length - 1] = arr[arr.length - 1];
        ends[arr.length - 1] = arr.length - 1;
        // 因为最后一个 length -1 上面已经得到，所以从 Length - 2 开始计算
        for (int i = arr.length - 2; i >= 0; i--) {
            // 如果之前累加和小于 0，现在最小累加和就是自己 + 前面最小累加和，则 ends[i] 就是前一个右边界到达的位置；
            if (sums[i + 1] < 0) {
                sums[i] = arr[i] + sums[i + 1];
                ends[i] = ends[i + 1];
                // 往右扩并不能使得自己的累加和最小，则就只有自己
            } else {
                sums[i] = arr[i];
                ends[i] = i;
            }
        }
// 下面为从 0 开始的扩充过程

        int right = 0;
        int sum = 0;
        int length = 0;
        // start 为每次开始扩的开始，right 为扩的右边界，本质上扩充到 right - 1 位置；
        for (int start = 0; start < arr.length; start++) {
            while (right < arr.length && sum + sums[right] <= aim) {
                sum += sums[right];
                right = ends[right] + 1;
            }
            // sum 减去 start 位置数，因为下面 start++ 了，重新来了
            sum -= right > start ? arr[start] : 0;
            length = Math.max(length, right - start);
            // 如果上来就扩不动，比如 100,200,7,3，-3，aim 等于 7，则100,200 都向右扩不动，需要变
            right = Math.max(right, start + 1);
        }
        return length;
    }

    // 使用二分加速的解法，O（NlogN）
    public static int maxLength(int[] arr, int k) {
        int[] h = new int[arr.length + 1];
        int sum = 0;
        h[0] = sum;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            h[i + 1] = Math.max(sum, h[i]);
        }
        sum = 0;
        int res = 0;
        int pre = 0;
        int len = 0;
        for (int i = 0; i != arr.length; i++) {
            sum += arr[i];
            pre = getLessIndex(h, sum - k);
            len = pre == -1 ? 0 : i - pre + 1;
            res = Math.max(res, len);
        }
        return res;
    }

    public static int getLessIndex(int[] arr, int num) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;
        int res = -1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (arr[mid] >= num) {
                res = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return res;
    }

    // for test
    public static int[] generateRandomArray(int len, int maxValue) {
        int[] res = new int[len];
        for (int i = 0; i != res.length; i++) {
            res[i] = (int) (Math.random() * maxValue) - (maxValue / 3);
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000000; i++) {
            int[] arr = generateRandomArray(10, 20);
            int k = (int) (Math.random() * 20) - 5;
            if (maxLengthAwesome(arr, k) != maxLength(arr, k)) {
                System.out.println("oops!");
            }
        }

    }

}
