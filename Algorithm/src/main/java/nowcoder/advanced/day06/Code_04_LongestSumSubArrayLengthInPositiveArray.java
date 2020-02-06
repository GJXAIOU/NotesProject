package nowcoder.advanced.advanced_class_06;

/**
 * 全正数数组中累加和为 aim 的最长子数组长度
 */
public class Code_04_LongestSumSubArrayLengthInPositiveArray {

    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        int left = 0;
        int right = 0;
        int sum = arr[0];
        int len = 0;
        while (right < arr.length) {
            if (sum == aim) {
                len = Math.max(len, right - left + 1);
                sum -= arr[left++];
            } else if (sum < aim) {
                right++;
                // 防止越界
                if (right == arr.length) {
                    break;
                }
                sum += arr[right];
                // sum > aim
            } else {
                sum -= arr[left++];
            }
        }
        return len;
    }

    public static int[] generatePositiveArray(int size) {
        int[] result = new int[size];
        for (int i = 0; i != size; i++) {
            result[i] = (int) (Math.random() * 10) + 1;
        }
        return result;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int len = 20;
        int k = 15;
        int[] arr = generatePositiveArray(len);
        printArray(arr);
        System.out.println(getMaxLength(arr, k));
    }
}
