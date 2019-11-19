package sort.bubblesort;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/** Java 版冒泡排序（从小到大）
 *
 *  算法步骤：
 * 比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 针对所有的元素重复以上的步骤，除了最后一个。
 * 持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 *
 * @author GJXAIOU
 * @create 2019-08-26-21:52
 */
public class BubbleSort {
    public static int[] bubbleSort(int[] sourceArray){
        if (sourceArray == null || sourceArray.length < 2){
            return sourceArray;
        }
        // 对原数组进行拷贝，不改变参数内容
        int[] copyArray = Arrays.copyOf(sourceArray, sourceArray.length);

        for (int i = 1; i < copyArray.length; i++) {
            // 设置标记，若值为 true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成
            boolean flag = true;

            for (int j = 0; j < copyArray.length - i; j++) {
                if (copyArray [j] > copyArray [j + 1]) {
                   swap(copyArray, j, j+1);
                    flag = false;
                }
            }
            if (flag) {
                break;
            }
        }
        return copyArray;
    }

    public static void swap(int[] arr, int i,int j){
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void main(String[] args) {
        // 输入一行数字，通过字符串分割并转换为数字存入数组
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        String[] stringArray = inputString.split(" ");
        int[] numArray = new int [stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            numArray[i] = Integer.parseInt(stringArray [i]);
        }

        int[] ansArray = BubbleSort.bubbleSort(numArray);
        for (int i : ansArray) {
            System.out.println(i);
         }
    }
}
