package nowcoder.easy.day01;

/**
 * @author GJXAIOU
 * @create 2019-10-04-20:08
 */

import java.util.Arrays;

public class QuickSort {
    /**
     *  首先调用该方法，可以设置排序的区间，默认为 0 ~ length-1；
     * @param sourceArray：需要排序的数组
     */
    public static void quickSort(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length < 2) {
            return;
        }
        quickSort(sourceArray, 0, sourceArray.length - 1);
    }

    /**
     * @param sourceArray：需要排序的数组
     * @param left：排序数组左边界，一般为：0
     * @param right：排序数组右边界，一般为：length - 1;
     *  less：小于参照元素区域的最右边边界：less = p[0] - 1;
     *  more：大于参照元素区域的最左边边界：more = p[1] + 1;
     *  p[0]：等于参照元素区域的最左边边界；
     *  p[1]：等于参数元素区域的最右边边界；
     *  小于参照元素区域：[Left ~ less];
     *  等于参照元素区域：[p[0] ~ p[1]]；
     *  大于参照元素区域：[more ~ right]；
     */
    public static void quickSort(int[] sourceArray, int left, int right) {
        if (left < right) {
            swap(sourceArray, left + (int) (Math.random() * (right - left + 1)), right);
            // p 数组中： p[0] 表示等于区域的左边界，p[1] 表示等于区域的右边界，
            // 左边区域：L ~ p[0] - 1;右边区域： p[1] + 1 ~ R;
            int[] p = partition(sourceArray, left, right);
            quickSort(sourceArray, left, p[0] - 1);
            quickSort(sourceArray, p[1] + 1, right);
        }
    }

    public static int[] partition(int[] sourceArray, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (sourceArray[left] < sourceArray[right]) {
                swap(sourceArray, ++less, left++);
            } else if (sourceArray[left] > sourceArray[right]) {
                swap(sourceArray, --more, left);
            } else {
                left++;
            }
        }
        swap(sourceArray, more, right);
        return new int[] { less + 1, more };
    }

    public static void swap(int[] sourceArray, int left, int right) {
        int tmp = sourceArray[left];
        sourceArray[left] = sourceArray[right];
        sourceArray[right] = tmp;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            quickSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        quickSort(arr);
        printArray(arr);

    }

}
