package nowcoder.easy.day01;

import java.util.Arrays;

/**
 * @author GJXAIOU
 * @create 2019-10-07-15:03
 * <p>
 * 分割数组
 * 注：该算法会调整数组中元素顺序，所以不能直接和对数器逐个值比较
 */
public class SplitArray {
    /**
     * @param sourceArray 输入的原数组
     * @param left        需要判断的数组左区间下标：默认为 0
     * @param right       需要判断的数组左区间下标：默认为 sourceArray.length - 1
     * @param tagMum      目标值
     */
    public static void sort(int[] sourceArray, int left, int right, int tagMum) {
        int less = left - 1;
        while (left <= right) {
            if (sourceArray[left] <= tagMum) {
                // 因为 less 的下一位就是大于 tagMum 的数
                // 如果没有这个判断，则如果数组最后一位则不会处理
                if (left == right) {
                    swap(sourceArray, left, ++less);
                }
                swap(sourceArray, left++, ++less);
            } else {
                left++;
            }
        }
        return;
    }

    public static void swap(int[] sourceArray, int left, int right) {
        if (left == right) {
            return;
        }
        sourceArray[left] = sourceArray[left] ^ sourceArray[right];
        sourceArray[right] = sourceArray[left] ^ sourceArray[right];
        sourceArray[left] = sourceArray[left] ^ sourceArray[right];
    }

    // 测试方法
    public static void main(String[] args) {
        int[] sourceArray = {1, 2, 8, -2, 4, 3, 4, 2, 8, 12, 3, 9, 8, 10, 9, 5, -1, 4, 9, 2};
        int[] sourceArray2 = copyArray(sourceArray);
        int left = 0;
        int right = sourceArray.length - 1;
        int tagMum = 6;

        System.out.println("原数组为：");
        for (int i : sourceArray) {
            System.out.print(i + " ");
        }

        sort(sourceArray, left, right, tagMum);

        System.out.println("\n分割之后数组为：");
        for (int i = 0; i < sourceArray.length; i++) {
            System.out.print(sourceArray[i] + " ");
        }

        System.out.println("\n---对数器输出结果-----");
        compare(sourceArray2, 6);
        for (int i = 0; i < sourceArray2.length; i++) {
            System.out.print(sourceArray2[i] + " ");
        }

        boolean equal = isEqual(sourceArray, sourceArray2, tagMum);
        System.out.println("\n" + equal);
    }

    // ---------对数器---------------------
    // 思路：遍历数组中每一个元素，小于等于 tagNum 放置于一个数组，大于 tagNum 放置于一个数组，最后将两个数组进行合并，可以保证和数组原顺序一致
    public static void compare(int[] sourceArray, int tagNum) {
        int[] lessArr = new int[sourceArray.length];
        int[] moreArr = new int[sourceArray.length];
        int less = 0;
        int more = 0;
        for (int i = 0; i < sourceArray.length; i++) {
            if (sourceArray[i] <= tagNum) {
                lessArr[less++] = sourceArray[i];
            } else {
                moreArr[more++] = sourceArray[i];
            }
        }
        for (int i = 0; i < more; i++) {
            lessArr[less++] = moreArr[i];
        }
        for (int i = 0; i < lessArr.length; i++) {
            sourceArray[i] = lessArr[i];
        }
        return;
    }


    public static int[] copyArray(int[] sourceArray) {
        int[] res = new int[sourceArray.length];
        if (sourceArray == null || sourceArray.length == 0) {
            return res;
        }
        for (int i = 0; i < sourceArray.length; i++) {
            res[i] = sourceArray[i];
        }
        return res;
    }

    // 依次比较两个数组在相同位置上元素和 tagNum 的关系
    public static boolean isEqual(int[] arr1, int[] arr2, int tagNum) {
        boolean[] booleanArr1 = new boolean[arr1.length];
        boolean[] booleanArr2 = new boolean[arr2.length];
        for (int i = 0; i < arr1.length; i++) {
            booleanArr1[i] = (arr1[i] <= tagNum);
            booleanArr2[i] = (arr2[i] <= tagNum);
        }
        return Arrays.equals(booleanArr1, booleanArr2);
    }
}
