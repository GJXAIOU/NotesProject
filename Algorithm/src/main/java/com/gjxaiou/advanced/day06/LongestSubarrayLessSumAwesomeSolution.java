package com.gjxaiou.advanced.day06;

public class LongestSubarrayLessSumAwesomeSolution {

    // 方式一：
    public static int maxLengthAwesome(int[] arr, int aim) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // 大步骤一：生成 min_sum 以及 min_sum_index
        int[] minSum = new int[arr.length];
        int[] minSumEndIndex = new int[arr.length];
        // 最后一个元素的 min_sum 就是自己，min_sum_index 也是自己的下标
        minSum[arr.length - 1] = arr[arr.length - 1];
        minSumEndIndex[arr.length - 1] = arr.length - 1;
        // 因为最后一个 length -1 上面已经得到，所以从 Length - 2 开始计算
        for (int i = arr.length - 2; i >= 0; i--) {
            // 如果之前累加和小于 0，现在最小累加和就是自己 + 前面最小累加和，则 minSumEndIndex[i] 就是前一个右边界到达的位置；
            // 因为后一个累加和小于 0 说明当前元素 > 当前元素 + 后一个元素，所以加上后面的总 sum 值更小
            if (minSum[i + 1] < 0) {
                minSum[i] = arr[i] + minSum[i + 1];
                minSumEndIndex[i] = minSumEndIndex[i + 1];
                // 往右扩并不能使得自己的累加和最小，则就只有自己
            } else {
                minSum[i] = arr[i];
                minSumEndIndex[i] = i;
            }
        }

        // 大步骤二：正式扩充过程
        int left = 0;
        int right = 0;
        int sum = 0;
        int length = 0;

        // left 为每次开始扩的开始，right 为扩的右边界，本质上扩充到 right - 1 位置；
        for (left = 0; left < arr.length; left++) {
            // 让 right 不断的往右扩充，直到不能扩充为止（到右边界或者和大于 aim）
            while (right < arr.length && sum + minSum[right] <= aim) {
                sum += minSum[right];
                right = minSumEndIndex[right] + 1;
            }
            // 当 right 扩充不动的时候记录以当前 left 位置上的元素开头的并且和小于等于 aim 的最长子数组长度
            // sum 减去 left 位置数，因为下面 left++ 了，重新来了
            sum -= right > left ? arr[left] : 0;
            length = Math.max(length, right - left);
            // 如果上来就扩不动，比如 100,200,7,3，-3，aim 等于 7，则100,200 都向右扩不动，需要变
            right = Math.max(right, left + 1);
        }
        return length;
    }

    // 方法二：使用二分加速的解法，O（NlogN）
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

    /////////////     测试程序     ////////////////
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
                System.out.println("oops，bad!");
            } else {
                System.out.println("ok");
            }
        }

    }

}
