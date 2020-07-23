package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:18
 */
public class LeetCode905 {
    public int[] sortArrayByParity(int[] A) {
        //求得数组长度
        int N = A.length;
        int index = 0;
        //遍历判断该数是否有偶数，使用快慢指针
        for (int i = 0; i < N; i++) {
            if (A[i] % 2 == 0) {
                int temp = A[index];
                A[index] = A[i];
                A[i] = temp;
                index++;
            }
        }
        return A;
    }
}
