package nowcoder.easy.day01;

import java.util.Arrays;

/**
 * 归并排序
 * 将整个数组分为两个部分，然后分别排序之后再使用外排进行合并
 */
public class MergeSort {

    public static void mergeSort(int[] sourceArray) {
        if (sourceArray == null || sourceArray.length < 2) {
            return;
        }
        mergeSort(sourceArray, 0, sourceArray.length - 1);
    }

    public static void mergeSort(int[] sourceArray, int left, int right) {
        if (left == right) {
            return;
        }
        // 求数组中间点，将数组划分为两部分
        int mid = left + ((right - left) >> 1);
        mergeSort(sourceArray, left, mid);
        mergeSort(sourceArray, mid + 1, right);
        merge(sourceArray, left, mid, right);
    }

    public static void merge(int[] sourceArray, int left, int mid, int right) {
        // 准备一个和原数组等长的辅助数组；
        int[] help = new int[right - left + 1];
        int i = 0;
        int startLeft = left;
        int startRight = mid + 1;
        while (startLeft <= mid && startRight <= right) {
            help[i++] = sourceArray[startLeft] < sourceArray[startRight] ? sourceArray[startLeft++] : sourceArray[startRight++];
        }
        // 上面的 while 循环会将一个数组中元素全部挪到 help 数组中，而另个数组还会剩余最后几个元素
        // 将剩余的一个数组中剩余的元素全部移到 help 数组中，这两个 while 只会执行一个
        while (startLeft <= mid) {
            help[i++] = sourceArray[startLeft++];
        }
        while (startRight <= right) {
            help[i++] = sourceArray[startRight++];
        }
        for (i = 0; i < help.length; i++) {
            sourceArray[left + i] = help[i];
        }
    }

    // --------使用对数器-------------------
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
            mergeSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                printArray(arr1);
                printArray(arr2);
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Bad！");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        mergeSort(arr);
        printArray(arr);

    }

}
