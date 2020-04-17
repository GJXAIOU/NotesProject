package array.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author GJXAIOU
 * @create 2020/04/13 12:54
 */
public class Offer45 {

    public String minNumber(int[] nums) {
        String[] copyNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            copyNums[i] = String.valueOf(nums[i]);
        // 对复制之后的数组进行排序
        fastSort(copyNums, 0, copyNums.length - 1);
        StringBuilder res = new StringBuilder();
        for (String s : copyNums)
            res.append(s);
        return res.toString();
    }

    void fastSort(String[] strArray, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = left, j = right;
        String tmp = strArray[i];
        while (i < j) {
            // a.compareTo(b) 可以理解为 a - b,返回值是 ASCII 码差值
            while ((strArray[j] + strArray[left]).compareTo(strArray[left] + strArray[j]) >= 0 && i < j) {
                j--;
            }
            while ((strArray[i] + strArray[left]).compareTo(strArray[left] + strArray[i]) <= 0 && i < j) {
                i++;
            }
            tmp = strArray[i];
            strArray[i] = strArray[j];
            strArray[j] = tmp;
        }
        strArray[i] = strArray[left];
        strArray[left] = tmp;
        fastSort(strArray, left, i - 1);
        fastSort(strArray, i + 1, right);
    }

    // 方法二：
    public String minNumber2(int[] nums) {
        String[] copyNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            copyNums[i] = String.valueOf(nums[i]);
        // 对复制之后的数组进行排序
        // 可以使用内置排序：Arrays.sort(copyNums, (x, y) -> (x + y).compareTo(y + x)); 代替 fastSort
        Arrays.sort(copyNums, new MyComparator());
        StringBuilder res = new StringBuilder();
        for (String s : copyNums)
            res.append(s);
        return res.toString();
    }

    class MyComparator implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            return (o1 + o2).compareTo(o2 + o1);
        }
    }
}


