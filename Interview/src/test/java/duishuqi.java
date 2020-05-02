import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/3/29 13:43
 */
public class duishuqi {
    /**
     * 排序对数器
     */
    //1.想要验证的方法，需要自己实现
    // 2.准备一个绝对正确的方法：这里使用系统自带的排序方法
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // 3.实现一个随机样本产生器：这里随机生成一个任意长度，值为任意的数组
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        // Math.random() 表示范围为： double [0,1) // (int)((maxSize + 1) * Math.random())：int [0,size]
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    // 因为是原地排序，会改变原数组，所以复制一份两个算法使用
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

    // 4.实现连个方法对比的方法
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

    // 可有可无
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // 最后：进行大样本测试
    public static void main(String[] args) {
        int testTime = 500000;
        // 长度为【0-100】
        int maxSize = 100;
        // 值为 【-100-100】之间
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);

            // 待测方法
            //bubbleSort(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Bad!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        // 首先调用原数组
        printArray(arr);


        // 待测方法
        // bubbleSort(arr);
        printArray(arr);
    }
}
