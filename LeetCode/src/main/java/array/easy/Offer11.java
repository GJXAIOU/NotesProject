package array.easy;

public class Offer11 {
    // 方法一：遍历或者排序得到最小值

    // 方法二：思路，求第二个非递减数组的开头
    public int minArray(int[] numbers) {
        if (numbers.length <= 0 || numbers == null) {
            return 0;
        }
        if (numbers.length == 1) {
            return numbers[0];
        }
        // 从第二个遍历，哪一个值比前一个小，则就是该值
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] < numbers[i - 1]) {
                return numbers[i];
            }
        }
        // 如果上面都没有，则可能没有旋转，则最小值就是第一个值。
        return numbers[0];
    }

    // 方法三：二分查找
    public int minArray2(int[] numbers) {
        if (numbers.length <= 0 || numbers == null) {
            return 0;
        }
        int begin = 0;
        int end = numbers.length - 1;
        while (begin < end) {
            int mid = (begin + end) >>> 1;
            if (numbers[mid] < numbers[end]) {
                end = mid;
            } else if (numbers[mid] > numbers[end]) {
                begin = mid + 1;
            } else {
                end--;
            }
        }
        return numbers[begin];

    }

}
