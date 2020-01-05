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


    public static void main(String[] args) {
        int[] arr = {0,2,1,-3,2,6,2};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i + "  ");
        }
    }
}
