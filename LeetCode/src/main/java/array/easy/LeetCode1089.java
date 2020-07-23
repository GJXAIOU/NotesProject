package array.easy;

import java.util.ArrayList;

/**
 * @author GJXAIOU
 * @create 2020/05/22 15:24
 */
public class LeetCode1089 {
    public void duplicateZeros(int[] arr) {
        ArrayList<Integer> resList = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                resList.add(0);
            }
            resList.add(arr[i]);
        }

        Object[] resArray = resList.toArray();
        // 注意这里是 arr.length
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) resArray[i];
        }
    }

    // 方法二：双指针
    public void duplicateZeros2(int[] arr) {
        int len = arr.length;
        int[] aux = arr.clone();

        int i = 0;
        int j = 0;

        boolean isZero = false;
        for (i = 0; i < len; i++) {
            if (!isZero) {
                int tmp = aux[j];
                arr[i] = tmp;
                if (tmp == 0) {
                    isZero = true;
                }
                j++;
            } else {
                isZero = false;
                arr[i] = 0;
            }
        }
        return;
    }
}
