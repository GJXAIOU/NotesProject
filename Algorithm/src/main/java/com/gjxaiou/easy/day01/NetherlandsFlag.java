package com.gjxaiou.easy.day01;

/**
 * @author GJXAIOU
 * 荷兰国旗问题
 */
public class NetherlandsFlag {
    /**
     * @param sourceArray：要分割的数组
     * @param left               ：数组左边界
     * @param right：数组右边界
     * @param tagNum：用于分割的参考数字
     * @return
     */
    public static int[] partition(int[] sourceArray, int left, int right, int tagNum) {
        // less 表示小于 tagNum 区域最右边数，more 是大于 tagNum 区域最左边数
        int less = left - 1;
        int more = right + 1;
        while (left < more) {
            if (sourceArray[left] < tagNum) {
                swap(sourceArray, ++less, left++);
            } else if (sourceArray[left] > tagNum) {
                // 从大于区域换过来的值还需要再次判断
                swap(sourceArray, --more, left);
            } else {
                left++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] sourceArray, int left, int right) {
        int tmp = sourceArray[left];
        sourceArray[left] = sourceArray[right];
        sourceArray[right] = tmp;
    }


    // for test
    public static int[] generateArray() {
        int[] arr = new int[20];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
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

    public static void main(String[] args) {
        int[] test = generateArray();
        System.out.println("测试数据为：");
        printArray(test);

        int[] res = partition(test, 0, test.length - 1, 5);
        System.out.println("划分之后数据为：值为原地修改");
        printArray(test);

        System.out.println("等于区域的开始下标为：");
        System.out.println(res[0]);
        System.out.println("等于区域的结束下标为：");
        System.out.println(res[1]);
    }
}
