package sort.bubblesort;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author GJXAIOU
 * @create 2019-08-27-11:12
 */
public class BubbleSortOpt {
    public static int[] bubbleSort(int[] sourceArray){
        // 对原数组进行拷贝，不改变参数内容
        int[] copyArray = Arrays.copyOf(sourceArray, sourceArray.length);

        int j,k;
        int flag = copyArray.length;
        while (flag > 0){
            k = flag;
            flag = 0;

            for (j = 0; j < k - 1; j++){
                if (copyArray[j] > copyArray[j + 1]){
                    int temp = copyArray [j];
                    copyArray [j] = copyArray [j + 1];
                    copyArray [j + 1] = temp;
                    flag = j;
                }
            }
        }
        return copyArray;
    }

    // 使用随机数进行测试
    public static void main(String[] args) {
        int[] inputArray = new int[10000000];
        Random random = new Random();
        for (int i : inputArray) {
            inputArray[i] = random.nextInt();
        }
        long currentTimeMillis = System.currentTimeMillis();
        int[] ans = BubbleSortOpt.bubbleSort(inputArray);
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
    }
}
