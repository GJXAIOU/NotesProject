package sort.selectsort;

/**
 * @author GJXAIOU
 * @create 2019-08-27-18:11
 */
public class SelectSortOpt {
    public static int[] selectSort(int[] sourceArray){
        int[] copyArray = new int [sourceArray.length];

        int left = 0;
        int right = copyArray.length - 1;
        for ( ;left < right; left++, right--){
            // 记录最大值和最小值
            int min = left;
            int max = right;

            for (int index = left; index <= right; index++){
                if (sourceArray[index] < sourceArray[min]) {
                    min = index;
                }
              //  if (a)
            }
        }
        return copyArray;
    }
}
