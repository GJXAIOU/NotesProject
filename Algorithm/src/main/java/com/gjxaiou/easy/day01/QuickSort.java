package com.gjxaiou.easy.day01;

/**
 * 随机快排
 *
 * @author GJXAIOU
 * @create 2019-10-04-20:08
 */

public class QuickSort {
    /**
     * 首先调用该方法，可以设置排序的区间，默认为 0 ~ length-1；
     *
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
     *                                 equalsPart[0]：等于参照元素区域的最左边边界；
     *                                 equalsPart[1]：等于参数元素区域的最右边边界；
     *                                 划分区域总结：
     *                                 小于参照元素区域：[Left ~ equalsPart[0] - 1];
     *                                 等于参照元素区域：[equalsPart[0] ~ equalsPart[1]]；
     *                                 大于参照元素区域：[equalsPart[1] + 1 ~ right]；
     */
    public static void quickSort(int[] sourceArray, int left, int right) {
        if (left < right) {
            // 将本部分数组最右边和本部分数组中任一元素进行交换
            swap(sourceArray, left + (int) (Math.random() * (right - left + 1)), right);
            int[] equalsPart = partition(sourceArray, left, right);
            quickSort(sourceArray, left, equalsPart[0] - 1);
            quickSort(sourceArray, equalsPart[1] + 1, right);
        }
    }

    /**
     * 以数组最后一个元素为标准，将整个数组划分为 小于、等于、大于 三个部分
     * less：小于参照元素区域的最右边边界：
     * more：大于参照元素区域的最左边边界：
     */
    public static int[] partition(int[] sourceArray, int left, int right) {
        int less = left - 1;
        int more = right;
        while (left < more) {
            if (sourceArray[left] < sourceArray[right]) {
                // 相当于不变，只是 less 和 left 分别向右移动一位
                swap(sourceArray, ++less, left++);
            } else if (sourceArray[left] > sourceArray[right]) {
                // 将该值放入大于区域的右边界
                swap(sourceArray, --more, left);
            } else {
                left++;
            }
        }
        // 因为 more 是大于区域左边边界，right 是等于区域的，所有应该交换
        swap(sourceArray, more, right);
        // 返回等于区域
        return new int[]{less + 1, more};
    }

    // 交换两下标对应元素
    public static void swap(int[] sourceArray, int left, int right) {
        int tmp = sourceArray[left];
        sourceArray[left] = sourceArray[right];
        sourceArray[right] = tmp;
    }

    // 测试程序
    public static void main(String[] args) {
        int[] arr = {43, -31, 10, -38, -42, -2, 22, 29, 30, 15, -60, -50, -13, 26, 3, 22, 27, 24,
                18, 18, 42, -40, 22, 8, 33, -52, -70, -55, 31, 42, 82, 19, -8, 8, 41, -35, 59, 65
                , -23, 3, -34, 65};
        System.out.println("原数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }

        quickSort(arr);
        System.out.println("\n排序后数组为：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + "  ");
        }
    }
}
//
//class MyQuickSort {
//    public static void quickSort(int[] inputArray) {
//        if (inputArray == null || inputArray.length <= 2) {
//            return;
//        }
//
//        quickSort(inputArray, 0, inputArray.length - 1);
//    }
//
//    public static void quickSort(int[] inputArray, int left, int right) {
//        if (left < right) {
//            swap(inputArray, right, left + ((int) Math.random() * (right - left + 1)));
//            int[] equalPart = partition(inputArray, left, right);
//            quickSort(inputArray, left, equalPart[0] - 1);
//            quickSort(inputArray, equalPart[1] + 1, right);
//        }
//    }
//
//    public static int[] partition(int[] inputArray, int left, int right) {
//        int less = left - 1;
//        int more = right;
//        while (left < more){
//          if (inputArray[left] < inputArray[right]){
//            swap(inputArray, ++less, left++);
//          }else if (inputArray[left] > inputArray[right]){
//              swap(inputArray, --more, left);
//          }else {
//              left++;
//          }
//        }
//        swap(inputArray, right, more);
//        return new int[]{less+ 1,more};
//
//    }
//
//
//    public static void swap(int[] inputArray, int left, int right) {
//       int temp = inputArray[left];
//       inputArray[left] = inputArray[right];
//       inputArray[right] = temp;
//    }
//
//    public static void main(String[] args) {
//        int[] ints = {1, 2, 3, 4, 23, 43, 2, 43, 2, 4};
//        quickSort(ints);
//        Arrays.stream(ints).forEach(System.out::println);
//    }
//}
