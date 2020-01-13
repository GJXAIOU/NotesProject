import java.util.Arrays;

/**
 * @Author GJXAIOU
 * @Date 2020/1/5 22:14
 */
public class BubbleSort {
    public static void bubbleSort(int[] sourceArr){
        if ((sourceArr == null)||(sourceArr.length == 0)){
            return;
        }
        for (int i = sourceArr.length - 1; i > 0; i--){
            for (int j = 0 ; j < i; j++){
                if (sourceArr[j] > sourceArr[i]){
                    swap(sourceArr,i,j);
                }
            }
        }
    }
    public static void swap(int[] arr, int i, int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }


    // --------------对数器--------------------------------
    // 准备一个绝对正确的方法
    public static void sort(int[] arr){
        Arrays.sort(arr);
    }

    // 准备一个随机样本产生器
    public static int[] generatedSample(int maxValue, int maxSize){
        int[] arr = new int[(int) (Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)((int)(maxValue + 1) * Math.random() - (int)maxValue * Math.random());
        }
        return arr;
    }

    public static int[] copyArray(int[] arr){
        int[] res = new int[arr.length];
        if (arr == null || arr.length == 0){
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // 准备一个比较方法
    public static boolean isEqual(int[] arr1, int[] arr2){
        if ((arr1 == null) && (arr2 != null) || (arr1 != null)&&(arr2 == null)){
            return false;
        }
        if (arr1.length != arr2.length ){
            return false;
        }
        if (arr1 == null && arr2 == null){
            return true;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] sample = generatedSample(100, 200);
        int[] ints = copyArray(sample);
        bubbleSort(sample);
        sort(ints);
        boolean equal = isEqual(sample, ints);
        System.out.println(equal);
    }

}
