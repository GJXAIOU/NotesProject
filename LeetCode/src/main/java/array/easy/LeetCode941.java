package array.easy;

/**
 * @author GJXAIOU
 * @create 2020/05/19 22:28
 */
public class LeetCode941 {
    public boolean validMountainArray(int[] A) {
        if (A.length < 3) {
            return false;
        }

        //防止一直都是递减数列这种
        if (A[0] >= A[1]) {
            return false;
        }
        boolean hasMountain = false;
        for (int i = 1; i < A.length; i++) {
            //只要出现 前后相等就一定是错的，后面不用考虑大于还是大于等于了 比较简单
            if (A[i] == A[i - 1]) {
                return false;
            }

            //到顶点就成立了
            if (A[i - 1] > A[i]) {
                hasMountain = true;
            }

            //前面判断成立了，到顶点了，如果后面也成立，就不是一直递减，则返回false
            if (hasMountain && A[i] > A[i - 1]) {
                return false;
            }
        }
        return hasMountain;
    }
}
