package sort.selectsort;

import java.util.Arrays;
import java.util.Scanner;

/** Java 版选择排序
 *
 * 算法步骤
 * 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。
 * 重复第二步，直到所有元素均排序完毕。
 *
 * @author GJXAIOU
 * @create 2019-08-27-16:40
 */
public class SelectSort {
    public static int[] selectSort(int[] sourceArray){
        int[] copyArray = Arrays.copyOf(sourceArray, sourceArray.length);

        // 一共经过 copyArray.length - 1 轮比较
        for (int i = 0; i < copyArray.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数为 copyArray.length - i
            for (int j = i + 1; j < copyArray.length; j++){
                if (copyArray[j] < copyArray[min]) {
                    // 记录当前能找到的最小值元素的下标
                    min = j;
                }
            }

            // 将找到的最小值和i位置所在的值进行交换
            if (i != min){
                int tmp = copyArray[i];
                copyArray[i] = copyArray[min];
                copyArray[min] = tmp;
            }
        }
        return copyArray;
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        String[] stringArray = inputString.split(" ");
        int[] numArray = new int[stringArray.length];
        for (int i = 0; i < stringArray.length; i++) {
            numArray[i] = Integer.parseInt(stringArray [i]);
        }

        int[] ansArray = SelectSort.selectSort(numArray);
        for (int i : ansArray) {
            System.out.println(i);
        }

    }
}
